package com.github.mianbaoshitou.syldelayretry;

import java.util.concurrent.Callable;

/**
 * FileName: TestCallable
 * Author:   sunyueling
 * Date:     2020/7/8 3:37 PM
 * Description:
 */
public class TestCallable implements Callable<Object> {
    private Long time ;

    public TestCallable(Long time) {
        this.time = time;
    }

    @Override
    public Long call() throws Exception {
        return time + 1;
    }
}
