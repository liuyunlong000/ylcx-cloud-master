package com.lcxbs.utils;

import cn.hutool.crypto.SmUtil;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

/**
 * Sm3加密工具类
 */
public class Sm3Utils {
    private static final String ENCODING = "UTF-8";
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * sm3算法加密
     * @param paramStr 待加密字符串
     * @return 返回加密后，固定长度=32的16进制字符串
     */
    public static String encrypt(String paramStr){
        // 将返回的hash值转换成16进制字符串
        String resultHexString = "";
        try {
            // 将字符串转换成byte数组
            byte[] srcData = paramStr.getBytes(ENCODING);
            // 调用hash()
            byte[] resultHash = hash(srcData);
            // 将返回的hash值转换成16进制字符串
            resultHexString = ByteUtils.toHexString(resultHash);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultHexString;
    }

    /**
     * 返回长度=32的byte数组
     * @explain 生成对应的hash值
     * @param srcData
     * @return
     */
    public static byte[] hash(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        //update the message digest with a single byte.
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        //close the digest, producing the final digest value.
        digest.doFinal(hash, 0);
        return hash;
    }
    /**
     * sm3算法加密
     * @param paramStr 待加密字符串
     * @param key 密钥
     * @return 返回加密后，固定长度=32的16进制字符串
     */
    public static String encryptPlus(String paramStr,String key){
        // 将返回的hash值转换成16进制字符串
        String resultHexString = "";
        try {
            // 将字符串转换成byte数组
            byte[] srcData = paramStr.getBytes(ENCODING);
            // 调用hash()
            byte[] resultHash = hmac(srcData,key.getBytes(ENCODING));
            // 将返回的hash值转换成16进制字符串
            resultHexString = ByteUtils.toHexString(resultHash);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultHexString;
    }

    /**
     * 通过密钥进行加密
     * @explain 指定密钥进行加密
     * @param key 密钥
     * @param srcData 被加密的byte数组
     * @return
     */
    public static byte[] hmac(byte[] key, byte[] srcData) {
        KeyParameter keyParameter = new KeyParameter(key);
        SM3Digest digest = new SM3Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] result = new byte[mac.getMacSize()];
        mac.doFinal(result, 0);
        return result;
    }

    /**
     * 判断源数据与加密数据是否一致
     * @explain 通过验证原数组和生成的hash数组是否为同一数组，验证2者是否为同一数据
     * @param srcStr 原字符串
     * @param sm3HexString 16进制字符串
     * @return 校验结果
     */
    public static boolean verify(String srcStr, String sm3HexString) {
        boolean flag = false;
        try {
            //使用指定的字符集将字符串编码为 byte 序列，并将结果存储到一个新的 byte 数组中
            byte[] srcData = srcStr.getBytes(ENCODING);
            //16进制 --> byte[]
            byte[] sm3Hash = ByteUtils.fromHexString(sm3HexString);
            byte[] newHash = hash(srcData);
            //判断数组是否相等
            if (Arrays.equals(newHash, sm3Hash)) {
                flag = true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return flag;
    }
   /**
     * sm3算法加密
     * @param paramStr 待加密字符串
     * @return 返回加密后，固定长度=32的16进制字符串
     * @explain
     */
    public static String encryptSm3ByHutool(String paramStr) {
        return SmUtil.sm3(paramStr);
    }
    /**
     * 判断源数据与加密数据是否一致(Hutool)
     * @param srcStr       原字符串
     * @param sm3HexString 16进制字符串
     * @return 校验结果
     */
    public static boolean verifySm3ByHutool(String srcStr, String sm3HexString) {
        boolean flag = false;
        if (sm3HexString.equals(encryptSm3ByHutool(srcStr))) {
            flag = true;
        }
        return flag;
    }
    /**
     * SM3加密方式之：不提供密钥的方式 SM3加密，返回加密后长度为64位的16进制字符串
     * @param src 明文
     * @return
     */
    public static String encrypt2(String src) {
        return ByteUtils.toHexString(getEncryptBySrcByte(src.getBytes()));
    }

    /**
     * 返回长度为32位的加密后的byte数组
     *
     * @param srcByte
     * @return
     */
    public static byte[] getEncryptBySrcByte(byte[] srcByte) {
        SM3Digest sm3 = new SM3Digest();
        sm3.update(srcByte, 0, srcByte.length);
        byte[] encryptByte = new byte[sm3.getDigestSize()];
        sm3.doFinal(encryptByte, 0);
        return encryptByte;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(Sm3Utils.encryptPlus("京QWE000","unix_timestamp"));
    }
}

