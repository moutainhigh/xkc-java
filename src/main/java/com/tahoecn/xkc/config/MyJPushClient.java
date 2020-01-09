package com.tahoecn.xkc.config;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.tahoecn.log.Log;
import com.tahoecn.log.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ProJectName: xkc
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2020-01-07 16:03
 * @Description: //TODO 极光推送客户端
 **/
@Component
public class MyJPushClient {

    private static final Log logger = LogFactory.get();

    @Value("${jpush.appKey}")
    private String appKey;

    @Value("${jpush.masterSecret}")
    private String masterSecret;

    @Value("${jpush.apnsProduction}")
    private boolean apnsProduction;

    private static JPushClient jPushClient = null;

    private static final int RESPONSE_OK = 200;

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:11 2020/1/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 获取推送客户端
     **/
    public JPushClient getJPushClient() {
        if (jPushClient == null) {
            jPushClient = new JPushClient(masterSecret, appKey);
        }
        return jPushClient;
    }

    /**
     * 发送给所有用户
     *
     * @param notificationTitle 通知内容标题
     * @param msgTitle          消息内容标题
     * @param msgContent        消息内容
     * @param extras        扩展字段
     */
    public Object sendToAll(String notificationTitle, String msgTitle, String msgContent, String extras) {
        PushPayload pushPayload = buildPushObject_android_and_ios(notificationTitle, msgTitle, msgContent, extras);
        PushResult pushResult = this.sendPush(pushPayload);
        if (null == pushResult) {
            return "推送失败！结果为null";
        }
        if (RESPONSE_OK == pushResult.getResponseCode()) {
            return 200;
        } else {
            return pushResult;
        }
    }

    /*
     * @Author zwc   zwc_503@163.com
     * @Date 16:16 2020/1/7
     * @Param
     * @return
     * @Version 1.0
     * @Description //TODO 推送
     **/
    private PushResult sendPush(PushPayload pushPayload) {
        logger.info("pushPayload={}", pushPayload);
        PushResult pushResult = null;
        try {
            // 调用工具包里面推送方法，别弄混了。
            pushResult = this.getJPushClient().sendPush(pushPayload);
            // 记录推送结果！
            logger.info("" + pushResult);
            if (pushResult.getResponseCode() == RESPONSE_OK) {
                logger.info("push successful, pushPayload={}", pushPayload);
            }
        } catch (APIConnectionException e) {
            logger.error("push failed: pushPayload={}, exception={}", pushPayload, e);
        } catch (APIRequestException e) {
            logger.error("push failed: pushPayload={}, exception={}", pushPayload, e);
        }

        return pushResult;
    }

    /**
     * 向所有平台所有用户推送消息
     *
     * @param notificationTitle
     * @param msgTitle
     * @param msgContent
     * @param extras
     * @return
     */
    public PushPayload buildPushObject_android_and_ios(String notificationTitle, String msgTitle, String msgContent, String extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert(notificationTitle)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(notificationTitle)
                                .setTitle(notificationTitle)
                                // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("androidNotification extras key", extras)
                                .build()
                        )
                        .addPlatformNotification(IosNotification.newBuilder()
                                // 传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(notificationTitle)
                                // 直接传alert
                                // 此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                // 此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("default")
                                // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("iosNotification extras key", extras)
                                // 此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)
                                .build()
                        )
                        .build()
                )
                // Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msgContent)
                        .setTitle(msgTitle)
                        .addExtra("message extras key", extras)
                        .build())
                .setOptions(Options.newBuilder()
                        // 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(apnsProduction)
                        // 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        // 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build())
                .build();
    }
}
