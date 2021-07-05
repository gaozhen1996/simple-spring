package com.gz.javastudy.netty.asyn;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
	private static String host = "127.0.0.1";

	private static int port = 8080;

	private static Socket socket;


	public static class Receive implements Runnable{

		@Override
		public void run() {
			if(socket == null){
				try {
					socket = new Socket(host,port);
					socket.setSoTimeout(60000);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				while (true){
					try {
						BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
						if (bufferedInputStream.available() > 0){
							byte[] receive = new byte[1024];
							int read = bufferedInputStream.read(receive);
							System.out.println("客服端收到消息："+new String(receive));
						} else {
							Thread.sleep(50);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}


	public static void main(String[] args){
		try {
			if(socket == null){
				socket = new Socket(host,port);
				socket.setSoTimeout(60000);
			}
			System.out.println("开始聊天");
			Scanner scanner = new Scanner(System.in);
			Thread thread = new Thread(new ClientSocket.Receive());
			thread.start();
			while (true){
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
				System.out.println("客服端请输入：");
				String s = "聊天结束！";
				if(!(s = scanner.nextLine()).equals("end")){
					bufferedOutputStream.write(s.getBytes());
					bufferedOutputStream.flush();
				} else {
					bufferedOutputStream.write(s.getBytes());
					bufferedOutputStream.flush();
					break;
				}
			}
			System.exit(0);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}