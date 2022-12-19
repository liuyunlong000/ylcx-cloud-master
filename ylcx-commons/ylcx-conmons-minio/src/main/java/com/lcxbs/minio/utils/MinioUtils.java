package com.lcxbs.minio.utils;


import cn.hutool.core.util.IdUtil;
import com.lcxbs.minio.entity.MinioProp;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author 周鵬翔
 * @Date 2021/12/20
 */
public class MinioUtils {

    /**
     * 连接url
     */
    public static final String endpoint="http://192.168.2.185:9000";
    /**
     * 用户名
     */
    public static final String accessKey="lcxbs";
    /**
     * 密码
     */
    public static final String secretKey="lcxbs123";

    /**
     * bucket
     */
    public static final String bucketName="design";

    /**
     * 默认块大小
     * 上传的时候必须设置分块大小。对象大小直接-1，分块大小[5m,5G]，参数的单位是B，
     * 所以最小单位是：5 * 1024 * 1024 = 5242880。minio支持分块传输
     */
    private final long defaultPartSize = 5242880;



    /** MinioClient */
    private MinioClient minioClient;

    //构造函数
    public static MinioUtils builder() {
        return new MinioUtils();
    }


    /**
     *初始化monio  默认环境
     * @author 周鹏翔
     * @date 2021/12/21
     * @param  * @param  默认环境
     * @return com.lcxbs.minio.MinioUtils
     */
    public MinioUtils minioClient() {
        this.minioClient=MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey,secretKey)
                .build();
        return this;
    }

    /**
     *初始化monio 指定环境
     * @author 周鹏翔
     * @date 2021/12/21
     * @param  * @param minioProp
     * @return com.lcxbs.minio.MinioUtils
     */
    public MinioUtils minioClient(MinioProp minioProp) {
        this.minioClient=MinioClient.builder()
                .endpoint(minioProp.endpoint)
                .credentials(minioProp.accessKey,minioProp.secretKey)
                .build();
        return this;
    }




    /**
     *桶是否存在
     * @author 周鹏翔
     * @date 2021/12/22
     * @param  * @param bucketName
     * @return boolean
     */
    public boolean bucketExists(String bucketName) throws Exception{
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }


    /**
     * 创建bucket
     *
     * @param bucketName bucket名称
     */

    public void createBucket(String bucketName) throws Exception{
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 获取全部bucket
     */
    public List<Bucket> getAllBuckets() throws Exception{
        return minioClient.listBuckets();
    }

    /**
     * 根据bucketName获取信息
     *
     * @param bucketName bucket名称
     */
    public Optional<Bucket> getBucket(String bucketName) throws Exception{
        return minioClient.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除信息
     *
     * @param bucketName bucket名称
     */
    public void removeBucket(String bucketName) throws Exception{
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    (单位秒) 过期时间 <=7
     * @return url
     */
    public String getObjectURL(String bucketName, String objectName, Integer expires) throws Exception{
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .expiry(expires)
                .method(Method.GET)
                .build());
        //.expiry(2, TimeUnit.HOURS)
        //.expiry(1, TimeUnit.DAYS)
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    public InputStream getObject(String bucketName, String objectName) throws Exception{
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, -1, defaultPartSize).build());
    }

    /**
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param fileName   上传文件路径
     * @throws Exception
     */
    public void putObject(String bucketName, String objectName, String fileName) throws Exception {
        minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(objectName).filename(fileName).build());
    }

    /**
     *上传文件
     * @author 周鹏翔
     * @date 2021/12/22
     * @param  * @param bucketName
     * @param fileName
     * @param stream
     * @param contentType
     * @return io.minio.ObjectWriteResponse
     */
    public ObjectWriteResponse putObject(String bucketName,String fileName,InputStream stream, String contentType)  throws Exception {
        return minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(stream, -1, defaultPartSize)
                .contentType(contentType)
                .build());
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        大小
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-minioClient-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, size, defaultPartSize).contentType(contextType).build());
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public StatObjectResponse getObjectInfo(String bucketName, String objectName) throws Exception {
        return minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 查询policy
     * @param bucketName
     * @return
     * @throws Exception
     */
    public String getBucketPolicy(String bucketName) throws Exception {
        return minioClient.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketName).build());
    }

    /**
     * 设置policy
     * @param bucketName
     * @return
     * @throws Exception
     */
    public void setBucketPolicy(String bucketName) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("    \"Statement\": [\n");
        builder.append("        {\n");
        builder.append("            \"Action\": [\n");
        builder.append("                \"s3:GetBucketLocation\",\n");
        builder.append("                \"s3:ListBucket\"\n");
        builder.append("            ],\n");
        builder.append("            \"Effect\": \"Allow\",\n");
        builder.append("            \"Principal\": \"*\",\n");
        builder.append("            \"Resource\": \"arn:aws:s3:::my-bucketname\"\n");
        builder.append("        },\n");
        builder.append("        {\n");
        builder.append("            \"Action\": \"s3:GetObject\",\n");
        builder.append("            \"Effect\": \"Allow\",\n");
        builder.append("            \"Principal\": \"*\",\n");
        builder.append("            \"Resource\": \"arn:aws:s3:::my-bucketname/myobject*\"\n");
        builder.append("        }\n");
        builder.append("    ],\n");
        builder.append("    \"Version\": \"2012-10-17\"\n");
        builder.append("}\n");
        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(builder.toString()).build());
    }
}
