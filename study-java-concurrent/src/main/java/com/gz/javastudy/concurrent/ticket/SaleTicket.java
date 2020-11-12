package com.gz.javastudy.concurrent.ticket;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SaleTicket implements Runnable{
	static Queue<Integer> tickets = new ConcurrentLinkedQueue<>();
	static int intTickets;
	private String saleWindows;
	boolean lockflag = true;
	static{
		//初始化票数据
		for (int i=1;i<=10;i++) {
			tickets.add(i);
		}
		intTickets = 10;
	}
	
	public SaleTicket(String saleWindows,ArrayList<Integer> bugTickets) {
		this.saleWindows = saleWindows;
	}
	
	@Override
	public void run() {
		if(lockflag) {
			while(!tickets.isEmpty()) {
		        System.out.println(saleWindows+"售出票号："+tickets.poll());
			}
		}else {
				while(intTickets>0) {
					synchronized(SaleTicket.class) {
						if(intTickets>0) {
							System.out.println(saleWindows+"售出票号："+intTickets);
							int temp = intTickets;
							temp = temp - 1;
							intTickets = temp;
						}
					}
				}
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
