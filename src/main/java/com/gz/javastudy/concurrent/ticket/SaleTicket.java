package com.gz.javastudy.concurrent.ticket;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SaleTicket implements Runnable{
	static Queue<Integer> tickets = new ConcurrentLinkedQueue<>();
	
	private String saleWindows;
	
	static{
		//初始化票数据
		for (int i=1;i<=50;i++) {
			tickets.add(i);
		}
	}
	
	public SaleTicket(String saleWindows,ArrayList<Integer> bugTickets) {
		this.saleWindows = saleWindows;
	}
	
	@Override
	public void run() {
		while(!tickets.isEmpty()) {
	        System.out.println(saleWindows+"售出票号："+tickets.poll());
		}
	}

	
	public static void main(String[] args) {
		ArrayList<Integer> bugTickets1 = new ArrayList<>();
		Thread thread1 = new Thread(new SaleTicket("窗口1",bugTickets1));
		ArrayList<Integer> bugTickets2 = new ArrayList<>();
		Thread thread2 = new Thread(new SaleTicket("窗口2",bugTickets2));
		ArrayList<Integer> bugTickets3 = new ArrayList<>();
		Thread thread3 = new Thread(new SaleTicket("窗口3",bugTickets3));
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
