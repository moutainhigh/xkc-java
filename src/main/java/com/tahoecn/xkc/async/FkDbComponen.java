//package com.tahoecn.xkc.async;
//
//import org.iq80.leveldb.DB;
//import org.iq80.leveldb.Options;
//import org.iq80.leveldb.impl.Iq80DBFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * @description: leveldb存储异步数据, 防止服务器中断丢失数据
// * @author: 张晓东
// * @time: 2020/4/29 15:00
// */
//public class FkDbComponen implements DbComponen {
//
//    private static final String DB_DIR = new StringBuffer()
//            .append(new File("").getAbsolutePath())
//            .append(File.separator)
//            .append("dbDir").toString();
//
//    private static DB db = null;
//
//    private static final FkDbComponen fkDbComponen = new FkDbComponen();
//
//    static {
//        try {
//            db = Iq80DBFactory.factory.open(new File(DB_DIR), new Options().createIfMissing(true));
//            Runtime.getRuntime().addShutdownHook(new Thread() {
//                public void run() {
//                    try {
//                        if (null != db) {
//                            db.close();
//                        }
//                    } catch (Exception e) {
//                    }
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private FkDbComponen() {
//    }
//
//    public static FkDbComponen getInstance() {
//        return fkDbComponen;
//    }
//
//    @Override
//    public void save(String dbKey, String dbValue) {
//        db.put(dbKey.getBytes(), dbValue.getBytes());
//    }
//
//    @Override
//    public String get(String dbKey) {
//        return new String(db.get(dbKey.getBytes()));
//    }
//
//    @Override
//    public void delete(String dbKey) {
//        db.delete(dbKey.getBytes());
//    }
//
//    @Override
//    public void close() throws IOException {}
//}
