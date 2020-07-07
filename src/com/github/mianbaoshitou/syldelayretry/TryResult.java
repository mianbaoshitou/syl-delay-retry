package com.github.mianbaoshitou.syldelayretry;

import java.util.ArrayList;

/**
 * FileName: TryResult
 * Author:   sunyueling
 * Date:     2020/7/7 2:54 PM
 * Description:
 */
public class TryResult implements ITryResult {
    private ArrayList<Long> timePoints;
    private Object result;
    TryResult(Object result){
        timePoints = new ArrayList<Long>();
        this.result = result;
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

    public Object getTryResult() {
        return null;
    }
}
