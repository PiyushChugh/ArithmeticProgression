package com.piyush;

import java.util.ArrayList;

/**
 * This class is used to find the sum of a few elements in an AP
 * @author Piyush Chugh
 *
 */
public class MyThread implements Runnable {
	
	private Thread thread;
	private int begin;
	private int end;
	private String threadName;
	private ArrayList<Integer> arithmeticProgression;
	static ArrayList<Integer> sumArray = new ArrayList<Integer>();
	
	public MyThread(int begin, int end, ArrayList<Integer> ap, String threadName) {
		this.begin = begin;
	    this.end = end;
	    this.threadName = threadName;
	    this.arithmeticProgression = ap;
	}

	public void run() {
		synchronized(arithmeticProgression) {
		    Integer sum =0;
		    for (int i = begin; i <= end; i++) {            
		        sum += arithmeticProgression.get(i);
		    }
		    sumArray.add(sum);
		}
	}
	
	public void start() {
	  if (thread == null) {
		  thread = new Thread(this, threadName);
		  thread.start();
	    }
	}
	
	public void join() throws InterruptedException {
	    thread.join();
	}

}
