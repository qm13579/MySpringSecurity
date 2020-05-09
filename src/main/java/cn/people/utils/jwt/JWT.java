package cn.people.utils.jwt;

import io.jsonwebtoken.Jwt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * jwt实体类
 */
public class JWT {
    //头部
    private String header;
    //负载
    private String payload;
    //签名
    private String signature;

    public JWT(String payload) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        this.header = JWTUtils.encode(JWTUtils.DEFAULT_HEADER);
        this.payload = JWTUtils.encode(payload);
        this.signature = JWTUtils.getSignature(payload);
    }

    public String getHeader() {
        return header;
    }

    public String getPayload() {
        return payload;
    }

    public String getSignature() {
        return signature;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return header+"."+ payload+"." +signature;
    }
}
