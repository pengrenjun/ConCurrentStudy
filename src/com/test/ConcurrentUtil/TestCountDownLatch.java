package com.test.ConcurrentUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 并发工具类CountDownLatch的使用 一个主线程阻塞 等待其余线程
 * @Author：pengrj
 * @Date : 2018/10/20 0020 11:21
 * @version:1.0
 */
public class TestCountDownLatch {

    final static CountDownLatch countDownLatch=new CountDownLatch(10);

    final static AtomicInteger   atomicInteger=new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService= Executors.newFixedThreadPool(10);

        for(int i=0;i<10;i++){

            UploadAttachTask uploadAttachTask=new UploadAttachTask(i,"文件"+i,countDownLatch,atomicInteger);

            executorService.execute(uploadAttachTask);
        }

        //主线程等待各个附件上传线程
        countDownLatch.await();
        executorService.shutdown();

        System.out.println("附件上传完毕 共上传附件数量"+atomicInteger.get());




    }


}
