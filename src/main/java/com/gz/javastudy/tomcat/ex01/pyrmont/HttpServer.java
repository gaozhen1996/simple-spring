package com.gz.javastudy.tomcat.ex01.pyrmont;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 表示一个web服务器
 * 测试效果，需要运行HttpServer类
 * 在浏览器中输入127.0.0.1:8080/index.html,就会出现页面
 */
public class HttpServer {

    /**
     * WEB_ROOT 是放html文件或者静态资源的目录
     *
     * 在Windows下的路径分隔符（\）和在Linux下的路径分隔符（/）是不一样的，
     * 当直接使用绝对路径时，跨平台会报No Such file or diretory异常。
     * 因此使用File.separator来保持跨平台性
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "src/main/resources/tomcat";

    /**
     * shutdown 命令
     */
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    /**
     * the shutdown command received
     */
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }
    
    public void await(){
        ServerSocket serverSocket = null;
        int port = 8080;
        try{
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e){
            e.printStackTrace();
        }
        while(!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try{
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();
                //创建一个Request和解析
                Request request = new Request(input);
                request.parse();
                //创建一个Response对象
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                //关闭socket
                socket.close();
                String uri = request.getUri();
                if(uri!=null)
                    shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
}
