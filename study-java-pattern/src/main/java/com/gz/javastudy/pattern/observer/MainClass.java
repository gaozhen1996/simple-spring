package com.gz.javastudy.pattern.observer;

public class MainClass {

	public static void main(String[] args) {
		
		Subject s = new Subject();
		s.attach(new Cat());
		s.attach(new Dog());
		s.notifyAllObservers();
		
	}

}
