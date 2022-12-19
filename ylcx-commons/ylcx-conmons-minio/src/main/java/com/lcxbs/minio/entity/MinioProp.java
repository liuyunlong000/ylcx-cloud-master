package com.lcxbs.minio.entity;

/**
 * @Author 周鵬翔
 * @Date 2021/12/20
 */
public class MinioProp {
    /**
     * 连接url
     */
    public  String endpoint;
    /**
     * 用户名
     */
    public  String accessKey;
    /**
     * 密码
     */
    public  String secretKey;

    /**
     * bucket
     */
    public  String bucketName;


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public MinioProp(String endpoint, String accessKey, String secretKey, String bucketName) {
        super();
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucketName = bucketName;
    }
}
