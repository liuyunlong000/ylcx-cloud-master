package com.lcxbs.wz.controller;

import com.baidu.ueditor.ActionEnter;
import com.lcxbs.core.BaseController;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@RestController
@RequestMapping("/ueditor")
@Configuration
public class UeditorController extends BaseController {
    @Value("${file.ueditor}")
    private String basePath;
    @RequestMapping(value = "/exec",produces = "application/javascript")
    public String exec(HttpServletRequest request) throws UnsupportedEncodingException, JSONException {
        request.setCharacterEncoding("utf-8");
        String rootPath=request.getRealPath("/");
        return new ActionEnter(request,rootPath,this.basePath).exec();
    }
    @RequestMapping(value = "/image/{fileName}", method = RequestMethod.GET)
    public void image(@PathVariable String fileName,HttpServletRequest request, HttpServletResponse response)  {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            String imgPath = this.basePath+"/image/"+fileName;
            ips = new FileInputStream(new File(imgPath));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ips != null) {
                try {
                    ips.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @RequestMapping(value = "/video/{fileName}", method = RequestMethod.GET)
    public void video(@PathVariable String fileName,HttpServletRequest request, HttpServletResponse response)  {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            String imgPath = this.basePath+"/video/"+fileName;
            ips = new FileInputStream(new File(imgPath));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ips != null) {
                try {
                    ips.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
