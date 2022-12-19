package com.lcxbs.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * 操作系统工具类，用于判断是否为window、获取本机IP
 *
 */
public class OSUtil {

    /**
     * 判断当前操作是否Windows.
     *
     * @return true---是Windows操作系统
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本机IP地址，并自动区分Windows还是Linux操作系统
     *
     * @return String IP地址
     */
    public static String getLocalIP() throws Exception {
        String sIP = "";
        InetAddress ip = null;
        try {
            // 如果是Windows操作系统
            if (isWindowsOS()) {
                ip = InetAddress.getLocalHost();
            } // 如果是Linux操作系统
            else {
                boolean bFindIP = false;
                Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                    // ----------特定情况，可以考虑用ni.getName判断
                    // 遍历所有ip
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = (InetAddress) ips.nextElement();
                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
                                && !ip.getHostAddress().contains(":")) {
                            bFindIP = true;
                            break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            throw e;
        }
        if (null != ip) {
            sIP = ip.getHostAddress();
        }
        return sIP;
    }

    /**
     * 获取操作系统名称
     *
     * @return
     */
    public static String getOSName() {
        Properties props = System.getProperties(); //获得系统属性集  
        String osName = props.getProperty("os.name"); //操作系统名称  
        return osName;
    }

    /**
     * 获取系统架构
     * @return 
     */
    public static String getOSArch() {
        Properties props = System.getProperties(); //获得系统属性集  
        String osArch = props.getProperty("os.arch"); //操作系统构架  
        return osArch;
    }

    /**
     * 获取系统版本
     * @return 
     */
    public static String getOSVersion() {
        Properties props = System.getProperties(); //获得系统属性集  
        String osVersion = props.getProperty("os.version"); //操作系统版本  
        return osVersion;
    }

    /**
     * 获取客户端IP地址，此方法用在proxy环境中
     *
     * @param request 请求
     * @return 客户端IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-real-ip");
        }
        // 解决请求和响应的IP一致且通过浏览器请求时，request.getRemoteAddr()为"0:0:0:0:0:0:0:1"
        if("0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                ip = getHostAddress();
            } catch (UnknownHostException e) {
            }
        }
        return ip;
    }

    /**
     * 根据网卡获取本机配置的IP
     * @return
     * @throws UnknownHostException
     */
    public static String getHostAddress()throws UnknownHostException {
        InetAddress inept = InetAddress.getLocalHost();
        return inept.getHostAddress();
    }

    /**
     * 判断字符串是否是一个IP地址
     *
     * @param addr 地址
     * @return true 是IP地址
     */
    public static boolean isIPAddr(String addr) {
        if (null == addr || addr.length() == 0) {
            return false;
        }
        String[] ips = StringUtils.split(addr, ".");
        if (ips.length != 4) {
            return false;
        }
        try {
            int ipa = Integer.parseInt(ips[0]);
            int ipb = Integer.parseInt(ips[1]);
            int ipc = Integer.parseInt(ips[2]);
            int ipd = Integer.parseInt(ips[3]);
            return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0 && ipc <= 255 && ipd >= 0 && ipd <= 255;
        } catch (Exception e) {
        }
        return false;
    }
}
