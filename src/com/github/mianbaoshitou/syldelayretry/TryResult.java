package com.github.mianbaoshitou.syldelayretry;

import java.util.ArrayList;
import java.util.UUID;

/**
 * FileName: TryResult
 * Author:   sunyueling
 * Date:     2020/7/7 2:54 PM
 * Description:
 */
public class TryResult implements ITryResult {
    private ArrayList<Long> timePoints;
    private UUID requestId;
    private Object result;
    TryResult(Object result){
        timePoints = new ArrayList<Long>();
        this.result = result;
        requestId = UUID.randomUUID();
    }


    public ArrayList<Long> getTryTimePoints() {
        return timePoints;
    }

    public int putTryAgainTime() {
        timePoints.add(System.nanoTime());
        return timePoints.size();
    }

    public int getTryTotalTimes() {
        return timePoints.size();
    }

    public long durationFromFirstTry() {
        if(timePoints.size() > 0){
            return System.nanoTime() - timePoints.get(0);
        }
        return 0;
    }

    @Override
    public UUID getRequestId() {
        return requestId;
    }

    public Object getTryResult() {
        return result;
    }

    @Override
    public void setTryResultData(Object data) {
        this.result = data;
    }


}
