package com.tahoecn.xkc.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.util.Optional;

public class FastJsonRedisSerializer implements RedisSerializer<Object> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    @Override
    public byte[] serialize(Object t) throws SerializationException {
        return Optional.ofNullable(t)
                .map(r -> JSON.toJSONString(r, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET))
                .orElseGet(() -> new byte[0]);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return Optional.ofNullable(bytes)
                .map(t -> JSON.parseObject(new String(t, DEFAULT_CHARSET), Object.class))
                .orElseGet(() -> null);
    }

}