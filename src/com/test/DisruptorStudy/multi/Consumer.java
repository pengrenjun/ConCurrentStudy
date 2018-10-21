package com.test.DisruptorStudy.multi;

import java.util.concurrent.atomic.AtomicInteger;

import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<Order>{
	
	private String consumerId;
	
	private static AtomicInteger count = new AtomicInteger(0);
	
	public Consumer(String consumerId){
		this.consumerId = consumerId;
	}

	@Override
	public void onEvent(Order order) throws Exception {
		count.incrementAndGet();
		System.out.println("��ǰ������: " + this.consumerId + "��������Ϣ��" + order.getId()+"��"+getCount()+"��������"+">AtomicInteger.hascode"+count.hashCode());

	}
	
	public int getCount(){
		return count.get();
	}

}
