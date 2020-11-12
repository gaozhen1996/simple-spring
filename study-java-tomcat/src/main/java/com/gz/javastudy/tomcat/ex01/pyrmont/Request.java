package com.gz.javastudy.tomcat.ex01.pyrmont;

import java.io.InputStream;

public class Request {
    private InputStream input;

    private String uri;

    public Request(InputStream input){
        this.input = input;
    }

    /**
     * 解析请求的原始数据
     */
    public void parse(){
    	  StringBuffer reqest = new StringBuffer(2048);
          int i;
          byte[] buffer = new byte[2048];
          try{
              i = input.read(buffer);
          }catch (Exception e){
              e.printStackTrace();
              i=-1;
          }
          for (int j=0;j<i;j++){
              reqest.append((char)buffer[j]);
          }
          System.out.println("HTTP请求报文:");
          System.out.println(reqest.toString());
          uri = parseUri(reqest.toString());
    }

    /**
     * 解析HTTP请求的URI
     * @param requestString
     * @return
     */
    private String parseUri(String requestString){
        int index1,index2;
        index1 = requestString.indexOf(' ');
        if(index1 != -1) {
        	index2 = requestString.indexOf(' ',index1+1);
        	if(index2>index1){
        	    return  requestString.substring(index1+1,index2);
            }
        }
        return null;
    }

    public String getUri(){
        return this.uri;
    }
}
