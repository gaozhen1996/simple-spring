package com.gz.javastudy.tomcat.ex02.pyrmont;


import javax.servlet.*;
import java.io.*;
import java.util.Locale;

/**
 * 实现ServletResponse接口
 */
public class Response implements  ServletResponse{

    private static final int BUFFER_SIZE = 1024;

    Request request;

    OutputStream output;

    PrintWriter writer;

    public Response(OutputStream output){
        this.output=output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException{
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        PrintStream writer = null;
        try {
            File file = new File(Constants.WEB_ROOT,request.getUri());
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

    public PrintWriter getWriter() throws IOException{
        writer = new PrintWriter(output,true);
        return writer;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public void setCharacterEncoding(String s) {

    }

    public void setContentLength(int i) {

    }

    public void setContentLengthLong(long l) {

    }

    public void setContentType(String s) {

    }

    public void setBufferSize(int i) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {
        return null;
    }
}
