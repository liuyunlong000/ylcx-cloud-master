package com.lcxbs.minio.service;

import cn.hutool.core.util.IdUtil;
import com.lcxbs.minio.entity.UploadResult;
import com.lcxbs.minio.utils.MimeTypeEnum;
import com.lcxbs.minio.utils.MinioUtils;
import com.lcxbs.minio.utils.ThumbnailsUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

/**
 * @Author 周鵬翔
 * @Date 2021/12/22
 */
public class FileService {

    /**
     *上传文件
     * @author 周鹏翔
     * @date 2021/12/22
     * @param  * @param file
     * @param bucketName
     * @param fileName
     * @param suffix
     * @return com.lcxbs.minio.entity.UploadResult
     */
    public static UploadResult uploadFile(MultipartFile file, String bucketName,String fileName,String suffix) throws Exception {

        // 判断存储桶是否存在 不存在创建
        MinioUtils.builder().minioClient().createBucket(bucketName);

        // 开始上传
        MinioUtils.builder().minioClient().putObject(bucketName,fileName,file.getInputStream(),file.getContentType());

        UploadResult uploadResult = new UploadResult();
        uploadResult.setFileType(file.getContentType());
        uploadResult.setFileSize(file.getSize());
        uploadResult.setFileSuffix(suffix);


        String name= fileName.substring(fileName.indexOf("/")+1,fileName.length());

        uploadResult.setFileName(name);
        uploadResult.setFileSize(file.getSize());
        uploadResult.setFileAddress(bucketName+"/" + fileName);

        //生成缩略图
        if (suffix.equals("png")||suffix.equals("jpg")||suffix.equals("jpeg")){
            InputStream is = ThumbnailsUtils.getThumbnailStream(suffix,bucketName,file.getInputStream());;
            String thumbnailsName="thumbnails"+"/"+fileName;
            MinioUtils.builder().minioClient().putObject(bucketName,thumbnailsName,is,file.getContentType());
            uploadResult.setThumbUrl(bucketName+"/" +thumbnailsName);
        }

        return uploadResult;
    }
    /**
     *上传文件
     * @author 周鹏翔
     * @date 2021/12/22
     * @param  * @param file
     * @param bucketName
     * @return com.lcxbs.minio.entity.UploadResult
     */
    public static UploadResult uploadFile(MultipartFile file, String bucketName) throws Exception {

        // 文件类型
        String suffix = MimeTypeEnum.getExtensionByMimeType(file.getContentType());

        //分类
        String fileType=MimeTypeEnum.getFileTypeBytMimeType(file.getContentType());

        // 新的文件名
        String fileName = fileType + "/" + IdUtil.simpleUUID() +"."+suffix;

        return uploadFile(file,bucketName,fileName,suffix);
    }


    /**
     *上传文件
     * @author 周鹏翔
     * @date 2021/12/22
     * @param  * @param file
     * @return com.lcxbs.minio.entity.UploadResult
     */
    public static UploadResult uploadFile(MultipartFile file) throws Exception {
        return uploadFile(file,MinioUtils.bucketName);
    }


    /**
     *获取文件访问路径
     * @author 周鹏翔
     * @date 2021/12/22
     * @param  * @param bucketName
     * @param objectName
     * @param expires
     * @return java.lang.String
     */
    public String getFileURL(String bucketName, String objectName, Integer expires) throws Exception {
        if (!MinioUtils.builder().minioClient().bucketExists(bucketName)){
            //"文件桶名称不存在";
        }
        return MinioUtils.builder().minioClient().getObjectURL(bucketName,objectName,expires);
    }

    /**
     *文件删除
     * @author 周鹏翔
     * @date 2021/12/22
     * @param  * @param bucketName
     * @param targetName
     * @return void
     */
    public void deleteFile(String bucketName, String targetName) throws Exception{
        if (!MinioUtils.builder().minioClient().bucketExists(bucketName)){
           //"文件桶名称不存在";
        }
        MinioUtils.builder().minioClient().removeObject(bucketName,targetName);
    }
}
