package com.github.mianbaoshitou.syldelayretry;

public class CommonDelayStrategies {
    /**
     * 固定时间延迟策略
     * 每次延时时间固定
     */

    /**
     * 指数延长策略
     * 以指数方式延时至最长间隔为止
     */

    /**
     * 慢开始策略(类似于TCP慢开始策略)
     * 在达到慢开始门限之前，每次延迟增长固定时间间隔
     * 当到达慢开始门限之后，每次延迟时间为慢开始门限时长
     */
}
