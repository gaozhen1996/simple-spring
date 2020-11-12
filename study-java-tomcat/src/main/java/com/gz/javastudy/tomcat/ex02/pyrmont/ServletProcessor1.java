package com.gz.javastudy.tomcat.ex02.pyrmont;

import javax.servlet.Servlet;
import java.net.URL;
import java.net.URLClassLoader;

public class ServletProcessor1 {
    public void process(Request request,Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader loader = null;
        @SuppressWarnings("rawtypes")
		Class myClass = null;
        try {
        	  //创建一个类载入器
            loader = new URLClassLoader(new URL[1]);
            //加载类
            myClass = loader.loadClass(Constants.servletMap.get(servletName));
        }catch (Exception e){
            e.printStackTrace();
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet)myClass.newInstance();
            servlet.service(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
