package com.hshooting.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadData {
	private static ExecutorService service = Executors.newCachedThreadPool();
	
	public static void addService(Runnable r){
		service.execute(r);
	}
	
	public static void shutdown(){
		service.shutdownNow();
	}
	
	public static ExecutorService getAllService(){
		return service;
	}
}
