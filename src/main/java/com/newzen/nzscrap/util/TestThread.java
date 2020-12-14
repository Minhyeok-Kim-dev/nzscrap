package com.newzen.nzscrap.util;

public class TestThread extends Thread{
	public static int threadCnt = 0;
	public static final int MAX_THREAD = 2;
	
	public TestThread(Runnable runnable) {
		super(runnable);
		
		while(threadCnt >= MAX_THREAD) {
			try {
				System.out.println("####### sleep");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}					
		}
		
		threadCnt++;
	}
	
	@Override
	public void run() {
		System.out.println("########### run");
		super.run();
		/*
		try {
			//Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
	}
}
