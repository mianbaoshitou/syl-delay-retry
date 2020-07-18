package com.github.mianbaoshitou.syldelayretry;

/**
 * FileName: TestDelayStrategy
 * Author:   sunyueling
 * Date:     2020/7/8 3:28 PM
 * Description: 测试延迟策略
 */
public class TestDelayStrategy implements IDelayStrategy {
    @Override
    public long nextRetryDelay(ITryResult tryResult) {
        System.out.println("计算延迟 tryResult = [" + tryResult.toString() + "]");
        return 1000 + System.currentTimeMillis();
    }
}
