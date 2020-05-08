package cn.people.utils.security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.print.DocFlavor;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * JWT工具类
 * @author : FENGZHI
 * create at:  2020/5/8  下午9:57
 * @description:
 */
public class JWTUtils {
    /**
     * 默认head
     */
    public static final String DEFAULT_HEADER = "{\"alg\":\"hs256\",\"typ\":\"JWT\"}";
    /**
     * hmacSHA256 加密算法 秘钥
     */
    public static final String SECRET = "123456";
    /**
     * token有效期 1天
     */
    public static final long EXPIRE_TIME = 1000*60*60*24;
    /**
     * token在header中的名字
     */
    public static final String HEADER_TOKEN_NAME = "Authorization";

    /**
     * base64 编码
     * @param input
     * @return
     */
    public static String encode(String input){
        return Base64.getUrlEncoder().encodeToString(input.getBytes());
    }

    /**
     * base64 解码
     * @param input
     * @return
     */
    public static String decode(String input){
        return new String(Base64.getUrlDecoder().decode(input));
    }

    /**
     * HMACSHA256 加密算法
     * @param data
     * @param secret
     * @return
     */
    public static String HMACSHA256(String data,String secret) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
        hmacSHA256.init(secret_key);
        byte[] array = hmacSHA256.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item :array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100),1,3);
        }
        return sb.toString().toUpperCase();
    }

}
