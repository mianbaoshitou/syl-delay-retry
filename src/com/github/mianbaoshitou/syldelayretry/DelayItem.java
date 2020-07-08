package com.github.mianbaoshitou.syldelayretry;

import java.util.UUID;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * FileName: DelayItem
 * Author:   sunyueling
 * Date:     2020/7/8 2:39 PM
 * Description: 单机延迟队列
 */
public class DelayItem implements Delayed {

    private long delayMillSecond;
    private UUID itemId;

    public DelayItem(long delayMillSecond, UUID itemId) {
        this.delayMillSecond = delayMillSecond;
        this.itemId = itemId;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return delayMillSecond - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        DelayItem item = (DelayItem) o;
        return this.delayMillSecond > item.delayMillSecond ? 1 : 0;
    }

    public UUID getItemId() {
        return itemId;
    }
}
