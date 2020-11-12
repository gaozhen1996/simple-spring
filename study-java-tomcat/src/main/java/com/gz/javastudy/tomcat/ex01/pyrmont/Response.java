package com.gz.javastudy.tomcat.ex01.pyrmont;

import java.io.*;

public class Response {
    private static final int BUFFER_SIZE = 1024;

    Request request;

    OutputStream output;

    public Response(OutputStream output){
        this.output = output;
    }

    public void setRequest(Request request){
        this.request = request;
    }

    /**
     * 发送静态资源
     * @throws IOException
     */
    public void sendStaticResource() throws IOException{
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        PrintStream writer = null;
        try {
        	String uri = request.getUri();
        	if(request.getUri()==null) {
        		uri = "index.html";
        	}
            File file = new File(HttpServer.WEB_ROOT,uri);
            if (file.exists()){
                //HTTP协议头部
                String responseHead = "HTTP/1.1 200 ok\r\nContent-Type: text/html\r\nContent-Length: "+file.length()+"\r\n\r\n";
                output.write(responseHead.getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(bytes,0,BUFFER_SIZE);
                while(ch!=-1){
                    output.write(bytes,0,ch);
                    ch = fis.read(bytes, 0x0,BUFFER_SIZE);
                }
            }else{
                System.out.println("文件不存在");
                String errorMessage =
                            "HTTP/1.1 404 File Not Found\r\n"+
                            "Content-Type: text/html\r\n" +
                            "Content-Length: 23\r\n" +
                            "\r\n" +
                            "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer!=null){
                writer.close();
            }
            if(fis!=null){
                fis.close();
            }
        }

    }
}
