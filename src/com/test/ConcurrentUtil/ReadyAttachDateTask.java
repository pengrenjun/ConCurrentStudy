package com.test.ConcurrentUtil;

import java.security.cert.CertificateNotYetValidException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:   准备上传附件的任务数据
 * @Author：pengrj
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
        String fileName="文件"+fileId;

        UploadAttachTask uploadAttachTask=new UploadAttachTask(fileId,fileName,new CountDownLatch(10),new AtomicInteger());

        System.out.println(Thread.currentThread().getName()+uploadAttachTask.toString()+"准备OK 进行等待!");
        //等待其余线程创建完毕数据
        try {
            this.cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        //最后一个准备线程准备OK 继续执行
        System.out.println(uploadAttachTask.toString()+"开始进行附件上传任务>>>>>>>>>>>>>>>>");

    }
}
