package com.test.DisruptorStudy.generate1;

import java.util.Random;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class TradeHandler2 implements EventHandler<Trade>, WorkHandler<Trade> {  
	  
	private static Random t=new Random();
    @Override  
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {  
        this.onEvent(event);  
    }  
  
    @Override  
    public void onEvent(Trade event) throws Exception {  
        //这里做具体的消费逻辑 
    	
        event.setId(String.valueOf(t.nextInt(100)));//简单生成下ID       
        System.out.println("消费线程:"+Thread.currentThread().getName()+":"+event.getId());  
    }  
}  