package com.myspring.demo.util;

public class Console {
	public static void info(String info) {
		System.out.println("\033[32;4minfo:" + info + "\033[0m");
	}
}
