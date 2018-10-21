package com.test.ConcurrentUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:   上传附件的任务
 * @Author：pengrj
 * @Date : 2018/10/20 0020 11:27
 * @version:1.0
 */
public class UploadAttachTask implements  Runnable {

    private Integer id;

    private String  fileName;


    private CountDownLatch countDownLatch;

    private AtomicInteger   atomicInteger;


    public UploadAttachTask(Integer id,String  fileName,CountDownLatch countDownLatch,AtomicInteger atomicInteger) {
        this.id=id;
        this.fileName=fileName;
        this.countDownLatch=countDownLatch;
        this.atomicInteger=atomicInteger;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()+" 附件开始上传:"+toString());

        try {
            TimeUnit.SECONDS.sleep(10);
            atomicInteger.incrementAndGet();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            countDownLatch.countDown();
        }

    }

    @Override
    public String toString() {
        return "UploadAttachTask{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
