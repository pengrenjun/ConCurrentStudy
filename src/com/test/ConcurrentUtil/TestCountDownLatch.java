package com.test.ConcurrentUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: ����������CountDownLatch��ʹ�� һ�����߳����� �ȴ������߳�
 * @Author��pengrj
 * @Date : 2018/10/20 0020 11:21
 * @version:1.0
 */
public class TestCountDownLatch {

    final static CountDownLatch countDownLatch=new CountDownLatch(10);

    final static AtomicInteger   atomicInteger=new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService= Executors.newFixedThreadPool(10);

        for(int i=0;i<10;i++){

            UploadAttachTask uploadAttachTask=new UploadAttachTask(i,"�ļ�"+i,countDownLatch,atomicInteger);

            executorService.execute(uploadAttachTask);
        }

        //���̵߳ȴ����������ϴ��߳�
        countDownLatch.await();
        executorService.shutdown();

        System.out.println("�����ϴ���� ���ϴ���������"+atomicInteger.get());




    }


}
