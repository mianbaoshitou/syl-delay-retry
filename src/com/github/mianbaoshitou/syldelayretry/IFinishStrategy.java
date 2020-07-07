package com.github.mianbaoshitou.syldelayretry;

/**
 * FileName: IFinishStrategy
 * Author:   sunyueling
 * Date:     2020/7/7 12:35 PM
 * Description: 用于决定是否结束重试
 */
public interface IFinishStrategy {
    /**
     * 重试判断
     * @param tryResult
     * @return
     */
    boolean finishPredicate(ITryResult tryResult);
}
