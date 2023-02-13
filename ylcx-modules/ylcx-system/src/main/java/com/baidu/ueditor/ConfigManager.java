//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.baidu.ueditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public final class ConfigManager {
    private final String rootPath;
    private final String originalPath;
    private final String contextPath;
    private static final String configFileName = "config.json";
    private String parentPath = null;
    private JSONObject jsonConfig = null;
    private static final String SCRAWL_FILE_NAME = "scrawl";
    private static final String REMOTE_FILE_NAME = "remote";

    private ConfigManager(String rootPath, String contextPath, String uri) throws FileNotFoundException, IOException {
        rootPath = rootPath.replace("\\", "/");
        this.rootPath = rootPath;
        this.contextPath = contextPath;
        this.originalPath = "config.json";
        /*if (contextPath.length() > 0) {
            this.originalPath = this.rootPath + uri.substring(contextPath.length());
        } else {
            this.originalPath = this.rootPath + uri;
        }*/
        this.initEnv();
    }

    public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {
        try {
            return new ConfigManager(rootPath, contextPath, uri);
        } catch (Exception var4) {
            return null;
        }
    }

    public boolean valid() {
        return this.jsonConfig != null;
    }

    public JSONObject getAllConfig() {
        return this.jsonConfig;
    }

    public Map<String, Object> getConfig(int type) {
        Map<String, Object> conf = new HashMap();
        String savePath = null;
        switch (type) {
            case 1:
                conf.put("isBase64", "false");
                conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
                conf.put("allowFiles", this.getArray("imageAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
                savePath = this.jsonConfig.getString("imagePathFormat");
                break;
            case 2:
                conf.put("filename", "scrawl");
                conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
                conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
                conf.put("isBase64", "true");
                savePath = this.jsonConfig.getString("scrawlPathFormat");
                break;
            case 3:
                conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
                conf.put("allowFiles", this.getArray("videoAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
                savePath = this.jsonConfig.getString("videoPathFormat");
                break;
            case 4:
                conf.put("isBase64", "false");
                conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
                conf.put("allowFiles", this.getArray("fileAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
                savePath = this.jsonConfig.getString("filePathFormat");
                break;
            case 5:
                conf.put("filename", "remote");
                conf.put("filter", this.getArray("catcherLocalDomain"));
                conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
                conf.put("allowFiles", this.getArray("catcherAllowFiles"));
                conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
                savePath = this.jsonConfig.getString("catcherPathFormat");
                break;
            case 6:
                conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
                conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
                conf.put("count", this.jsonConfig.getInt("fileManagerListSize"));
                break;
            case 7:
                conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
                conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
                conf.put("count", this.jsonConfig.getInt("imageManagerListSize"));
        }

        conf.put("savePath", savePath);
        conf.put("rootPath", this.rootPath);
        return conf;
    }

    private void initEnv() throws FileNotFoundException, IOException {
        /*File file = new File(this.originalPath);
        if (!file.isAbsolute()) {
            file = new File(file.getAbsolutePath());
        }
        this.parentPath = file.getParent();
        String configContent = this.readFile(this.getConfigPath());*/
        String configContent="{\n" +
                "    \"imageActionName\": \"uploadimage\",\n" +
                "    \"imageFieldName\": \"upfile\",\n" +
                "    \"imageMaxSize\": 2048000,\n" +
                "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "    \"imageCompressEnable\": false,\n" +
                "    \"imageCompressBorder\": 1600,\n" +
                "    \"imageInsertAlign\": \"none\",\n" +
                "    \"imageUrlPrefix\": \"/ueditor/image/\",\n" +
                "    \"imagePathFormat\": \"/image/{time}{rand:6}\",\n" +
                "\n" +
                "    \"scrawlActionName\": \"uploadscrawl\",\n" +
                "    \"scrawlFieldName\": \"upfile\",\n" +
                "    \"scrawlPathFormat\": \"/image/{time}{rand:6}\",\n" +
                "    \"scrawlMaxSize\": 2048000,\n" +
                "    \"scrawlUrlPrefix\": \"/ueditor/image/\",\n" +
                "    \"scrawlInsertAlign\": \"none\",\n" +
                "\n" +
                "    \"snapscreenActionName\": \"uploadimage\",\n" +
                "    \"snapscreenPathFormat\": \"/image/{time}{rand:6}\",\n" +
                "    \"snapscreenUrlPrefix\": \"/common/ueditor/image/\",\n" +
                "    \"snapscreenInsertAlign\": \"none\",\n" +
                "\n" +
                "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"],\n" +
                "    \"catcherActionName\": \"catchimage\",\n" +
                "    \"catcherFieldName\": \"source\",\n" +
                "    \"catcherPathFormat\": \"/image/{time}{rand:6}\",\n" +
                "    \"catcherUrlPrefix\": \"/ueditor/image/\",\n" +
                "    \"catcherMaxSize\": 2048000,\n" +
                "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "\n" +
                "    \"videoActionName\": \"uploadvideo\",\n" +
                "    \"videoFieldName\": \"upfile\",\n" +
                "    \"videoPathFormat\": \"/video/{time}{rand:6}\",\n" +
                "    \"videoUrlPrefix\": \"/ueditor/video/\",\n" +
                "    \"videoMaxSize\": 102400000,\n" +
                "    \"videoAllowFiles\": [\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"],\n" +
                "\n" +
                "    \"fileActionName\": \"uploadfile\",\n" +
                "    \"fileFieldName\": \"upfile\",\n" +
                "    \"filePathFormat\": \"/file/{time}{rand:6}\",\n" +
                "    \"fileUrlPrefix\": \"/ueditor/file/\",\n" +
                "    \"fileMaxSize\": 51200000,\n" +
                "    \"fileAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
                "    ],\n" +
                "\n" +
                "    \"imageManagerActionName\": \"listimage\",\n" +
                "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\",\n" +
                "    \"imageManagerListSize\": 20,\n" +
                "    \"imageManagerUrlPrefix\": \"\",\n" +
                "    \"imageManagerInsertAlign\": \"none\",\n" +
                "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "\n" +
                "    \"fileManagerActionName\": \"listfile\",\n" +
                "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\",\n" +
                "    \"fileManagerUrlPrefix\": \"\",\n" +
                "    \"fileManagerListSize\": 20,\n" +
                "    \"fileManagerAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
                "    ]\n" +
                "}";
        try {
            JSONObject jsonConfig = new JSONObject(configContent);
            this.jsonConfig = jsonConfig;
        } catch (Exception var4) {
            this.jsonConfig = null;
        }

    }

    private String getConfigPath() {
        return this.parentPath + File.separator + "config.json";
    }

    private String[] getArray(String key) {
        JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
        String[] result = new String[jsonArray.length()];
        int i = 0;

        for(int len = jsonArray.length(); i < len; ++i) {
            result[i] = jsonArray.getString(i);
        }

        return result;
    }

    private String readFile(String path) throws IOException {
        StringBuilder builder = new StringBuilder();

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
            BufferedReader bfReader = new BufferedReader(reader);
            String tmpContent = null;

            while((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }

            bfReader.close();
        } catch (UnsupportedEncodingException var6) {
        }

        return this.filter(builder.toString());
    }

    private String filter(String input) {
        return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
    }
}
