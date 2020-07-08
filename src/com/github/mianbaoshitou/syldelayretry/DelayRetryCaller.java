package com.github.mianbaoshitou.syldelayretry;

import com.sun.xml.internal.ws.util.CompletedFuture;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * FileName: DelayRetryCaller
 * Author:   sunyueling
 * Date:     2020/7/7 1:44 PM
 * Description: 重试发起类
 */

public class DelayRetryCaller<U> {





    private IDelayStrategy delayStrategy;
    private IFinishStrategy finishStrategy;
    private String routingKey;

//    private Callable<U> task;
    private static final ConcurrentHashMap<UUID, CompletableFuture> runningTasks = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<UUID, Callable> tasksCallable = new ConcurrentHashMap<>();
    BlockingQueue<Pair<Future, UUID>> taskDelayRetryQ = new LinkedBlockingQueue<>();
    DelayQueue<DelayItem> delayQueue = new DelayQueue<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    public CompletableFuture call(Callable<U> callable){
        try {

            // 接收到任务
            UUID requestId = UUID.randomUUID();

            // 将任务提交到线程池,返回当次任务Future
            Future<U> future = executorService.submit(callable);
            // 将 future 放入延迟重试监视队列
            taskDelayRetryQ.offer(new Pair<>(future, requestId));

            // 生成给调用方的凭据
            CompletableFuture<ITryResult> clientRequestFuture = new CompletableFuture<>();
            //将两者关联
            runningTasks.put(requestId, clientRequestFuture);
            tasksCallable.put(requestId, callable);
            //返回给调用者关联的 future
//            U result = task.call();

            return clientRequestFuture;
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void futureTaskMonitor(){
        //启动任务监视线程
        Thread monitorThread = new Thread(new Runnable() {

            public void run() {
                while (true){
                    try {
                        Pair<Future, UUID> taskPair = taskDelayRetryQ.poll();
                        if(null != taskPair) {
                            //取出 future, 查看是否已经完成
                            Future future = taskPair.getKey();
                            if (future.isDone()) {
                                //获取线程池中执行结果
                                final Object result = future.get();
                                TryResult tryResult = new TryResult(result);
                                if (finishStrategy.finishPredicate(tryResult)) {

                                    //结束的任务,将CompletableFuture设置为结束并返回结果
                                    runningTasks.get(taskPair.getValue()).complete(tryResult.getTryResult());
                                    //任务完成, 清理数据
                                    tasksCallable.remove(taskPair.getValue());
                                    runningTasks.remove(taskPair.getValue());
                                } else {
                                    //计算下次发起延时间隔
                                    long nextRetryDelay = delayStrategy.nextRetryDelay(tryResult);
                                    //放入延时消息队列
                                    delayQueue.offer(new DelayItem(nextRetryDelay, taskPair.getValue()));
                                }
                            } else {
                                //重新加入监视队列
                                taskDelayRetryQ.offer(taskPair);
                            }
                        }

                        //检查延时队列
                        DelayItem delayItem = delayQueue.poll();
                        if(null != delayItem){
                            System.out.println("获取到延迟任务" + LocalDateTime.now());
                            Callable callable = tasksCallable.get(delayItem.getItemId());
                            Future<U> future = executorService.submit(callable);
                            // 将 future 放入延迟重试监视队列
                            taskDelayRetryQ.offer(new Pair<>(future, delayItem.getItemId()));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

            }
            }
        });

        monitorThread.start();
    }


    public IDelayStrategy getDelayStrategy() {
        return delayStrategy;
    }

    public void setDelayStrategy(IDelayStrategy delayStrategy) {
        this.delayStrategy = delayStrategy;
    }

    public IFinishStrategy getFinishStrategy() {
        return finishStrategy;
    }

    public void setFinishStrategy(IFinishStrategy finishStrategy) {
        this.finishStrategy = finishStrategy;
    }
}
