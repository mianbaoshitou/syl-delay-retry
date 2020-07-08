package com.github.mianbaoshitou.syldelayretry;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * FileName: ITryResult
 * Author:   sunyueling
 * Date:     2020/7/7 12:38 PM
 * Description:
 */
public interface ITryResult<R> {

    /**
     * 获取所有重试时间记录-纳秒
     * @return
     */
    ArrayList<Long> getTryTimePoints();

    /**
     * 新增重试时间-当前时间
     * @return
     */
    int putTryAgainTime();

    /**
     * 重试总次数
     * @return
     */
    int getTryTotalTimes();

    /**
     * 从第一次到现在的时间间隔
     * @return
     */
    long durationFromFirstTry();

    /**
     * 客户端请求唯一 ID
     * @return
     */
    UUID getRequestId();

    /**
     * 获取执行结果
     * @return
     */
    public R getTryResult();

}
