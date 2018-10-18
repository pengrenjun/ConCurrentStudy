package com.test.ProductCustomerModel;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{

	private BlockingQueue<Data> queue;
	
	public Consumer(BlockingQueue queue){
		this.queue = queue;
	}

    //多线程间是否启动变量，有强制从主内存中刷新的功能。即时返回线程的状态
    private volatile boolean isRunning = true;
	//随机对象
	private static Random r = new Random(); 

	@Override
	public void run() {
		while(isRunning){
			try {
				//获取数据
				Data data = this.queue.take();
				//进行数据处理。休眠0 - 1000毫秒模拟耗时
				Thread.sleep(r.nextInt(1000));
				System.out.println("当前消费线程：" + Thread.currentThread().getName() + "， 消费成功，消费数据为id: " + data.getId());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop(){
	    this.isRunning=false;
    }
}
