//package com.tahoecn.xkc.async;
//
//import java.io.Closeable;
//import java.io.IOException;
//import java.util.Queue;
//import java.util.concurrent.LinkedBlockingQueue;
//
///**
// * @description: 单例Queue
// * @author: 张晓东
// * @time: 2020/4/29 15:01
// */
//public class FkDataQueue implements Closeable {
//
//
//    private static final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(2000);
//
//    /**
//     * 初始化查询leveldb数据放进Queue中
//     */
//    static {
//
//    }
//
//    private static FkDataQueue fkDataQueue  = new FkDataQueue();
//
//    private FkDataQueue() {}
//
//    public static FkDataQueue getInstance() {
//        return fkDataQueue;
//    }
//
//    /**
//     *
//     * @description: 添加一个元素
//     * @author: 张晓东
//     * @time: 2020/4/29 15:35
//     */
//    public void put(String msg) throws Exception {
//        FkDataQueue.queue.put(msg);
//    }
//
//    /**
//     *
//     * @description: 移除并返回队列头部的元素
//     * @return:
//     * @author: 张晓东
//     * @time: 2020/4/29 15:35
//     */
//    public String take() throws Exception {
//        return FkDataQueue.queue.take();
//    }
//
//    @Override
//    public void close() throws IOException {}
//
//}
