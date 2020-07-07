package com.github.mianbaoshitou.syldelayretry;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.*;

/**
 * FileName: DelayRetryCaller
 * Author:   sunyueling
 * Date:     2020/7/7 1:44 PM
 * Description: 重试发起类
 */

public class DelayRetryCaller<U> {



    private DelayRetryCaller(){};

    private IDelayStrategy delayStrategy;
    private IFinishStrategy finishStrategy;
    private String routingKey;

    private Callable<U> task;
    private static final ConcurrentHashMap<CompletableFuture, Future> runningTasks = new ConcurrentHashMap<>();
    BlockingQueue<FutureTask> taskQ = new LinkedBlockingQueue<FutureTask>();
    BlockingQueue<CompletableFuture<ITryResult>> taskDelayRetryQ = new LinkedBlockingQueue<CompletableFuture<ITryResult>>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    public CompletableFuture call(String retryMark){
        try {
            //将任务提交到线程池,返回 future
            Future<U> future = executorService.submit(task);
            //将 future 放入延迟重试监视队列
            CompletableFuture<ITryResult> completedFuture = new CompletableFuture<>();
            taskDelayRetryQ.offer(completedFuture);

            //将两者关联
            runningTasks.put(completedFuture, future);
            //返回给调用者关联的 future
//            U result = task.call();
            return completedFuture;
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void futureTaskMonitor(){
        //启动任务监视线程
        Thread monitorThread = new Thread(new Runnable() {

            public void run() {
                try {
                    CompletableFuture<ITryResult> task = taskDelayRetryQ.take();
                   //取出 future, 查看是否已经完成
                    Future future = runningTasks.get(task);
                    if(future.isDone()){
                        //获取线程池中执行结果
                        final Object result = future.get();
                        TryResult tryResult = new TryResult(result);
                        if(finishStrategy.finishPredicate(tryResult)){

                        }else {
                            //计算下次发起延时间隔
                            long nextRetryDelay = delayStrategy.nextRetryDelay(tryResult);
                            //放入延时消息队列
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        monitorThread.start();
    }
}
