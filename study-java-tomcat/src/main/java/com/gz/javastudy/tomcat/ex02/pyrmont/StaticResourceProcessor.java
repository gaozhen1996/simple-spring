package com.gz.javastudy.tomcat.ex02.pyrmont;

import java.io.IOException;

/**
 * 用于对处理静态资源的请求
 */
public class StaticResourceProcessor {

    /**
     *
     * @param request
     * @param response
     * 调用reponse.sendStaticResource()来处理静态资源
     */
    public void process(Request request,Response response){
        try {
            response.sendStaticResource();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
