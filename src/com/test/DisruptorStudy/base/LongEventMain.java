package com.test.DisruptorStudy.base;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventMain {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		//创建缓冲池
		ExecutorService  executor = Executors.newCachedThreadPool();
		//创建工厂
		LongEventFactory factory = new LongEventFactory();
		//创建bufferSize ,也就是RingBuffer大小，必须是2的N次方
		int ringBufferSize = 1024 * 1024; // 

		/**
		//BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
		WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
		//SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
		WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
		//YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
		WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
		*/
		
		//创建disruptor
		/**
		 * 1.第一个参数为工厂类对象,用于创建一个个的event对象 event对象为实际的消费对象
		 * 2.第二个参数为缓冲区的大小
		 * 3.第三个参数  线程池 进行Disraptor内部数据的处理及调度
		 * 4.第四个参数  ProducerType.SINGLE(单个生产者) 和 ProducerType.MULTI(多个生产者) 用来表明生产者的数量   
		 * 5.第五个参数 是一种生产与消费的策略 对生产和消费做协调和调度
		 */
		Disruptor<LongEvent> disruptor = 
				new Disruptor<LongEvent>(factory, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
		// 连接消费事件方法  设置事件监听处理器
		disruptor.handleEventsWith(new LongEventHandler());
		
		// 启动
		disruptor.start();
		
		//Disruptor 的事件发布过程是一个两阶段提交的过程：
		//发布事件 
		//使用该方法 从Disruptor获取存放数据的环形数据结构容器的 RingBuffer
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		
		LongEventProducer producer = new LongEventProducer(ringBuffer); 
		//LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		for(long l = 0; l<100; l++){
			byteBuffer.putLong(0, l);
			producer.onData(byteBuffer);
			//Thread.sleep(1000);
		}

		
		disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
		executor.shutdown();//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；		
	
		
	}
}
