package com.lcxbs.minio.entity;

/**
 * @Author 周鵬翔
 * @Date 2021/12/22
 */
public class UploadResult {


    /** 文件上传时的名称 */
    private String fileName;
    /** 文件mime类型 */
    private String fileType;
    /** 附件后缀名 */
    private String fileSuffix;
    /** 文件大小,单位字节 */
    private Long fileSize;
    /** 文件地址 */
    private String fileAddress;
    /** 缩略图地址 */
    private String thumbUrl;
    /** 图片宽度 */
    private Long imageWidth;
    /** 图片高度 */
    private Long imageHeight;
    /** 文件时长(视频、语音) */
    private Long fileTime;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public Long getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Long imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Long getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Long imageHeight) {
        this.imageHeight = imageHeight;
    }

    public Long getFileTime() {
        return fileTime;
    }

    public void setFileTime(Long fileTime) {
        this.fileTime = fileTime;
    }
}
