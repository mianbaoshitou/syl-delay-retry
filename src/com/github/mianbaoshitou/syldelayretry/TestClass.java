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
        DelayRetryCaller<Object> retryCaller = new DelayRetryCaller<>();
        retryCaller.setDelayStrategy(testDelayStrategy);
        retryCaller.setFinishStrategy(testFinishStrategy);
        TestCallable testCallable = new TestCallable(1l);
        TestCallable testCallable2 = new TestCallable(1l);
        retryCaller.call(testCallable);
        CompletableFuture completableFuture = retryCaller.call(testCallable2);

        retryCaller.futureTaskMonitor();
        try {
            Object o = completableFuture.get();
            System.out.println("args = [" + o + "]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
