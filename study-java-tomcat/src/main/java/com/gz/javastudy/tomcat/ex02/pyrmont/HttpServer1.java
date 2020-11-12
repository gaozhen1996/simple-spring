package com.gz.javastudy.tomcat.ex02.pyrmont;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 等待HTTP请求，为每个请求创建Request和Response对象，
 * 更加HTTP请求的是静态资源还是servlet,将改HTTP请求分发给
 * 一个StaticResourceProcessor实例或者一个ServletProcessor实例。
 */
public class HttpServer1 {
    /**
     * shutdown 命令
     */
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    /**
     * the shutdown command received
     */
    private boolean shutdown = false;

    public static void main(String[] args) throws Exception {
        HttpServer1 httpServer = new HttpServer1();
        System.out.println("服务启动");
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

                //判断请求的是静态资源还是Servlet
                if(request.getUri().startsWith("/servlet")){
                    ServletProcessor1 processor1 = new ServletProcessor1();
                    processor1.process(request,response);
                }else{
                    StaticResourceProcessor staticResourceProcessor =
                            new StaticResourceProcessor();
                    staticResourceProcessor.process(request,response);
                }

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
