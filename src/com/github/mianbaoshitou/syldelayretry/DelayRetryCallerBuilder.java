package com.github.mianbaoshitou.syldelayretry;

public class DelayRetryCallerBuilder {

    private IDelayStrategy delayStrategy;
    private IFinishStrategy finishStrategy;

    public static DelayRetryCallerBuilder newDelayRetryBuilder(){
        return new DelayRetryCallerBuilder();
    }


    //设置延迟策略
    public DelayRetryCallerBuilder withDelayStrategy(IDelayStrategy delayStrategy){
        this.delayStrategy = delayStrategy;
        return this;
    }

    //设置结束策略
    public DelayRetryCallerBuilder withFinishStrategy(IFinishStrategy finishStrategy){
        this.finishStrategy = finishStrategy;
        return this;
    }

    public DelayRetryCaller build(){
        return new DelayRetryCaller(this.delayStrategy, this.finishStrategy);
    }
}
