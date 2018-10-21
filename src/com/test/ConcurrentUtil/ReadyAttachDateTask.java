package com.test.ConcurrentUtil;

import java.security.cert.CertificateNotYetValidException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:   ׼���ϴ���������������
 * @Author��pengrj
 * @Date : 2018/10/20 0020 12:04
 * @version:1.0
 */
public class ReadyAttachDateTask implements Runnable {




    private CyclicBarrier cyclicBarrier;

    Random random=new Random();

    public ReadyAttachDateTask(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        int fileId=random.nextInt(100);
        String fileName="�ļ�"+fileId;

        UploadAttachTask uploadAttachTask=new UploadAttachTask(fileId,fileName,new CountDownLatch(10),new AtomicInteger());

        System.out.println(Thread.currentThread().getName()+uploadAttachTask.toString()+"׼��OK ���еȴ�!");
        //�ȴ������̴߳����������
        try {
            this.cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        //���һ��׼���߳�׼��OK ����ִ��
        System.out.println(uploadAttachTask.toString()+"��ʼ���и����ϴ�����>>>>>>>>>>>>>>>>");

    }
}
