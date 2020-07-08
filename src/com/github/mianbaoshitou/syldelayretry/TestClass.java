package com.github.mianbaoshitou.syldelayretry;

import java.util.concurrent.Callable;

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
        TestCallable testCallable = new TestCallable(1);
        TestCallable testCallable2 = new TestCallable(1);
        retryCaller.call(testCallable);
        retryCaller.call(testCallable2);
        retryCaller.futureTaskMonitor();
    }


}
