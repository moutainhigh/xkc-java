package com.tahoecn.xkc.async;

import com.tahoecn.xkc.model.risk.BWangxiaobao;

/**
 * @description: 旺小宝推送人脸刷证数据后, 风控监控处理
 *
 * @author: 张晓东
 * @time: 2020/5/11 16:13
 */
public class FaceRynnable extends BaseRunnable {

    private BWangxiaobao bWangxiaobao;

    public FaceRynnable(BWangxiaobao bWangxiaobao) {
        this.bWangxiaobao = bWangxiaobao;
    }

    @Override
    void invoke() throws Exception {
        super.log.info("人脸识别线程   1111111111");
    }
}
