package com.tahoecn.xkc.common.utils;

import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.tahoecn.xkc.common.constants.AesConstants;
import com.tahoecn.xkc.common.exception.AesException;
/**
 * AES解密解密工具类
 * @author 张晓曦
 *
 */
public class AesUtils {
	private static final String CHARSET_NAME = "UTF-8";
    private static final String AES_NAME = "AES";
    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final String IV = AesConstants.AES_IV;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 加密
     */
    public static String encrypt(@NotNull String content, @NotNull String key) throws AesException {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            return ParseSystemUtil.parseByte2HexStr(cipher.doFinal(content.getBytes(CHARSET_NAME)));
        } catch (Exception ex) {
        	ex.printStackTrace();
            throw new AesException("加密失败");
        }
    }

    /**
     * 解密
     */
    public static String decrypt(@NotNull String content, @NotNull String key) throws AesException {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            return new String(cipher.doFinal(Objects.requireNonNull(ParseSystemUtil.parseHexStr2Byte(content))), CHARSET_NAME);
        } catch (Exception ex) {
        	ex.printStackTrace();
            throw new AesException("解密失败");
        }
    }

}
