package com.github.mianbaoshitou.syldelayretry;

/**
 * FileName: IDelayMessageProvider
 * Author:   sunyueling
 * Date:     2020/7/7 7:07 PM
 * Description: 延迟消息服务
 */
public interface IDelayMessageProvider {
    boolean offerDelayMessage(ITryResult tryMessage, long delay);
    boolean subscribe(String routingKey);
    boolean unSubscribe();
}
