package com.tahoecn.xkc.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 创建线程异步处理风控需求
 * @author: 张晓东
 * @time: 2020/4/27 13:59
 */
public class ExecutorsUtils {

    //风控业务执行线程池
    private static Executor poolExecutor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() / 2,
            Runtime.getRuntime().availableProcessors(), 60L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("fk_work_thread_poll").build()
    );

    /**
     * @description: 风控执行器方法
     *
     * @return:
     * @author: 张晓东
     * @time: 2020/4/27 14:14
     */
    public static void fkExecute(Runnable r) {
        poolExecutor.execute(r);
    }

}
