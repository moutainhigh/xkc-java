package com.tahoecn.xkc.common.constants;

public final class MQConstant {
    private MQConstant() {
    }

    //exchange name
    public static final String DEFAULT_EXCHANGE = "opening.PhoneMassageChange";
    //TTL QUEUE
    public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "opening.massage.dead.letter.queue";
    //DLX repeat QUEUE 死信转发队列
    public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "opening.massage.repeat.trade.queue";
    //消息队列名称
    public static final String MASSAGE_QUEUE_NAME = "opening.PhoneMassage";

    public static final String PLACE_ORDER_QUEUE = "opening.place.order.queue";
    public static final String ORDER_EXCHANGE = "opening.order.exchange";
    public static final String ORDER_KEY = "opening.order.key";
}
