package com.test.ProductCustomerModel;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{

	private BlockingQueue<Data> queue;
	
	public Consumer(BlockingQueue queue){
		this.queue = queue;
	}

    //���̼߳��Ƿ�������������ǿ�ƴ����ڴ���ˢ�µĹ��ܡ���ʱ�����̵߳�״̬
    private volatile boolean isRunning = true;
	//�������
	private static Random r = new Random(); 

	@Override
	public void run() {
		while(isRunning){
			try {
				//��ȡ����
				Data data = this.queue.take();
				//�������ݴ�������0 - 1000����ģ���ʱ
				Thread.sleep(r.nextInt(1000));
				System.out.println("��ǰ�����̣߳�" + Thread.currentThread().getName() + "�� ���ѳɹ�����������Ϊid: " + data.getId());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop(){
	    this.isRunning=false;
    }
}
