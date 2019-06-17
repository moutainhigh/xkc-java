package com.tahoecn.xkc.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scheduled
{
    public abstract String cron();

    public abstract long fixedDelay();

    public abstract long fixedRate();
}

//fixedDelay: 设定间隔时间为 5000ms
//        fixedRate: 设定固定速率, 以固定速率5s来执行
//        cron="0 0 2 * * ? ": 使用时间表达式