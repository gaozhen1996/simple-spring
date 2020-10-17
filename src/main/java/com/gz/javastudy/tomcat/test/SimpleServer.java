package com.gz.javastudy.tomcat.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class SimpleServer {

    public static void main(String[] args) {

        try {

            // 初始化服务端socket连接，并监听8888端口的socket请求
            @SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(8500);

            System.out.println("****** I am Server, now begin to wait the client ******");

            // 处理socket请求
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                InputStream  inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader   bufferedReader = new BufferedReader(inputStreamReader);
                String str;
                 if ((str = bufferedReader.readLine()) != null) {
                         System.out.println(str);
                 }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}