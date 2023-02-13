//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.baidu.ueditor.upload;

import com.baidu.ueditor.define.State;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
public class Uploader {
    private HttpServletRequest request = null;
    private Map<String, Object> conf = null;

    private String basePath=null;

    public Uploader(HttpServletRequest request, Map<String, Object> conf,String basePath) {
        this.request = request;
        this.conf = conf;
        this.basePath=basePath;
    }

    public final State doExec() {
        String filedName = (String)this.conf.get("fieldName");
        State state = null;
        if ("true".equals(this.conf.get("isBase64"))) {
            state = Base64Uploader.save(this.request.getParameter(filedName), this.conf);
        } else {
            state = BinaryUploader.save(this.request, this.conf,this.basePath);
        }

        return state;
    }
}
