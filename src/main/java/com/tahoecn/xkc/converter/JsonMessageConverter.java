package com.tahoecn.xkc.converter;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.tahoecn.xkc.common.constants.AesConstants;
import com.tahoecn.xkc.common.exception.AesException;
import com.tahoecn.xkc.common.utils.AesUtils;

/**
 * 返回参数加密封装converter
 * @author 张晓曦
 *
 */
public class JsonMessageConverter extends FastJsonHttpMessageConverter {

	private static final Logger log = LoggerFactory.getLogger(JsonMessageConverter.class);

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {
        OutputStream out = outputMessage.getBody();
        try {
            String jsonString = JSON.toJSONString(object);
            log.debug("[writeInternal]======>返回明文数据：{}" + jsonString);
            //对返回数据进行AES加密
            jsonString = AesUtils.encrypt(jsonString, AesConstants.AES_KEY);
            log.debug("[writeInternal]======>返回加密数据：{}" + jsonString);
            out.write(jsonString.getBytes());
        } catch (AesException e) {
            e.printStackTrace();
            log.error("[writeInternal]======>", e);
        }
        out.close();
    }
    @Override
    protected boolean supports(Class<?> clazz) {
        // 表明只处理ResponseMessage类型的参数。
        //return ResponseMessage.class.isAssignableFrom(clazz);
        return false;
    }
}
