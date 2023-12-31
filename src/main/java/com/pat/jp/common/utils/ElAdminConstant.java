package com.pat.jp.common.utils;

/**
 * @Description: 常用静态常量
 *
 * @author: huangwei
 * @date: 2023-07-12
 * @time: 13:12
 **/
public class ElAdminConstant {

    /**
     * 用于IP定位转换
     */
    public static final String REGION = "内网IP|内网IP";
    /**
     * win 系统
     */
    public static final String WIN = "win";

    /**
     * mac 系统
     */
    public static final String MAC = "mac";

    /**
     * 常用接口
     */
    public static class Url {
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }
}
