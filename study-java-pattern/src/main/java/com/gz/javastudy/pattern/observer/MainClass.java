package com.gz.javastudy.pattern.observer;

public class MainClass {

	public static void main(String[] args) {
		
		Master s = new Master();
		s.attach(new Cat());
		s.attach(new Dog());
		s.notifyAllObservers();
		
	}

}
