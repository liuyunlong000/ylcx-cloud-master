package com.lcxbs.minio.utils;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author 周鵬翔
 * @Date 2021/12/22
 */
public class ThumbnailsUtils {

    public static  InputStream  getThumbnailStream(String suffix,String bucketName,InputStream inputStream) throws Exception {
        //生成缩略图
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        BufferedImage bufferedImage= Thumbnails.of(inputStream).scale(0.8).outputQuality(0.25f).asBufferedImage();

        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
        if (ImageIO.write(bufferedImage, suffix, imOut)){
            InputStream stream = new ByteArrayInputStream(bs.toByteArray());
            return stream;
        }
        return inputStream;
    }
}
