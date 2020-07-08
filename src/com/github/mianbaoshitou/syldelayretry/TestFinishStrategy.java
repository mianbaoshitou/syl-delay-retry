package com.github.mianbaoshitou.syldelayretry;

/**
 * FileName: TestFinishStrategy
 * Author:   sunyueling
 * Date:     2020/7/8 3:27 PM
 * Description: 测试停止策略
 */
public class TestFinishStrategy implements IFinishStrategy {
    @Override
    public boolean finishPredicate(ITryResult tryResult) {
        return false;
    }
}
