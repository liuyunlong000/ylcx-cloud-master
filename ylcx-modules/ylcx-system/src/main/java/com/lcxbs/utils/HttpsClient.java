package com.lcxbs.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * HTTPS请求客户端
 */
public class HttpsClient{

    protected static Logger logger = Logger.getLogger(HttpsClient.class);
    private static String result;
    private static String GET = "GET";
    private static String POST = "POST";
    private static String TLS = "TLS";

    public static void main(String[] args) throws IOException {
        String uri = "https://www.baidu.com";
        String bytes = doGet(uri);
        System.out.println(bytes);

        //创建JSONObject对象
        JSONObject json = new JSONObject();
        //向json中添加数据
        json.put("phone", "1212");
        json.put("cphm", "3333");
        json.put("khflag", 0);
        System.out.println(HttpsClient.doPost("https://dt01.catsic.com:11008/carInfo/showList",json.toString()));
    }

    public static String doGet(String uri) throws IOException {
        HttpsURLConnection httpsConn = getHttpsURLConnection(uri, GET);
        return getBytesFromStream(httpsConn.getInputStream());
    }

    public static String doPost(String uri, String data) throws IOException {
        HttpsURLConnection httpsConn = getHttpsURLConnection(uri, POST);
        setBytesToStream(httpsConn.getOutputStream(), data.getBytes());
        return getBytesFromStream(httpsConn.getInputStream());
    }

    private static HttpsURLConnection getHttpsURLConnection(String uri, String method) throws IOException {
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance(TLS);
            ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SSLSocketFactory ssf = ctx.getSocketFactory();

        URL url = new URL(uri);
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
        httpsConn.setSSLSocketFactory(ssf);
        httpsConn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        httpsConn.setRequestMethod(method);
        httpsConn.setDoInput(true);
        httpsConn.setDoOutput(true);
        // 设置请求头
        httpsConn.setRequestProperty("Content-Type", "application/json");
        httpsConn.setRequestProperty("token", "172911d614514db285b8163dc45f4b11");
        return httpsConn;
    }

    private static void setBytesToStream(OutputStream os, byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        byte[] kb = new byte[1024];
        int len;

        try{
            while ((len = bais.read(kb)) != -1) {
                os.write(kb, 0, len);
            }
            os.flush();
        } catch (IOException e){
            e.printStackTrace();
        }

        try{
            if(os != null){
                os.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            if(bais != null){
                bais.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static String getBytesFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] kb = new byte[1024];
        int len;

        try{
            while ((len = is.read(kb)) != -1) {
                baos.write(kb, 0, len);
            }
            baos.flush();
        } catch (IOException e){
            e.printStackTrace();
        }

        try{
            if(baos != null){
                baos.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            if(is != null){
                is.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        result = new String(baos.toByteArray());
        return result;
    }

    private static final class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
