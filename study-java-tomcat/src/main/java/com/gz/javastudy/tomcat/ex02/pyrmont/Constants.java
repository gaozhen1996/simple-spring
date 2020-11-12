package com.gz.javastudy.tomcat.ex02.pyrmont;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    /**
     * WEB_ROOT 是放html文件或者静态资源的目录
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "src/main/resources/tomcat";

    public static final Map<String,String> servletMap = new HashMap<String, String>();
    
    static {
    		servletMap.put("PrimitiveServlet", "com.gz.javastudy.tomcat.ex02.pyrmont.PrimitiveServlet");
    }
}

