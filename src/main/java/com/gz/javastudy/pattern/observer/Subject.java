package com.gz.javastudy.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

	private List<ICallbackService> observers = new ArrayList<ICallbackService>();
	
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		notifyAllObservers();
	}

	public void attach(ICallbackService observer) {
		observers.add(observer);
	}

	public void notifyAllObservers() {
		for (ICallbackService observer : observers) {
			observer.callback();
		}
	}
}
