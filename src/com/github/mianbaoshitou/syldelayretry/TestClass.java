package com.github.mianbaoshitou.syldelayretry;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * FileName: TestClass
 * Author:   sunyueling
 * Date:     2020/7/8 3:29 PM
 * Description:
 */
public class TestClass {
    public static void main(String[] args) {
        TestDelayStrategy testDelayStrategy = new TestDelayStrategy();
        TestFinishStrategy testFinishStrategy = new TestFinishStrategy();
//        DelayRetryCaller<Object> retryCaller = new DelayRetryCaller<>();
        DelayRetryCaller retryCaller = new DelayRetryCallerBuilder().withDelayStrategy(testDelayStrategy).withFinishStrategy(testFinishStrategy).build();
        TestCallable testCallable11 = new TestCallable(100l);
        TestCallable testCallable12 = new TestCallable(150l);
        retryCaller.call(testCallable11);
        CompletableFuture completableFuture = retryCaller.call(testCallable12);
//        retryCaller.futureTaskMonitor();

        System.out.println("启动第二批");
        DelayRetryCaller retryCaller2 = new DelayRetryCallerBuilder().withDelayStrategy(testDelayStrategy).withFinishStrategy(testFinishStrategy).build();
        TestCallable testCallable21 = new TestCallable(200l);
        TestCallable testCallable22 = new TestCallable(250l);
        retryCaller2.call(testCallable21);

        CompletableFuture completableFuture22 = retryCaller2.call(testCallable22);

//        retryCaller2.futureTaskMonitor();
        try {
//            Object o = completableFuture.get();
            Object o2 = completableFuture22.get();
            System.out.println("args = [" + o2 + "]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
