package com.github.mianbaoshitou.syldelayretry;

/**
 * 常用停止策略
 */
public class CommonFinishStrategies {


    /**
     * 从不停止策略
     */
    private static final class AlwayRunStratege implements IFinishStrategy{

        @Override
        public boolean finishPredicate(ITryResult tryResult) {
            return false;
        }
    }

    /**
     * 根据重试次数停止
     */
    private static final class totalTryTimes implements IFinishStrategy{
        @Override
        public boolean finishPredicate(ITryResult tryResult) {
            return false;
        }
    }




    /**
     * 根据首次运行时间间隔
     */
    private static final class totalTryTime implements IFinishStrategy{
        @Override
        public boolean finishPredicate(ITryResult tryResult) {
            return false;
        }
    }

    /**
     * 根据最晚运行时间-迟于指定时间后任务不再运行
     */

    private static final class finishBefore implements IFinishStrategy{
        @Override
        public boolean finishPredicate(ITryResult tryResult) {
            return false;
        }
    }
}
