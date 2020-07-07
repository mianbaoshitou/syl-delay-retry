package com.github.mianbaoshitou.syldelayretry;

/**
 * FileName: IDelayStrategy
 * Author:   sunyueling
 * Date:     2020/7/7 12:40 PM
 * Description: 延时策略, 生成下次重试时间
 */
public interface IDelayStrategy {

    /**
     * 下次发起重试的延迟时间
     * @param tryResult
     * @return
     */
    long nextRetryDelay(ITryResult tryResult);
}
