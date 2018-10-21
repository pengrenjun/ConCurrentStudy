package com.test.ConcurrentUtil;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 并发工具类CyclicBarrier的使用 一个主线程定义准备阻塞等待的线程数 待所有子线程准备OK 所有子线程一起工作
 * @Author：pengrj
 * @Date : 2018/10/20 0020 11:59
 * @version:1.0
 */
public class TestCyclicBarrier {



    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier=new CyclicBarrier(10);

        ExecutorService executorService= Executors.newFixedThreadPool(10);

        for(int i=0;i<10;i++){

            ReadyAttachDateTask readyAttachDateTask=new ReadyAttachDateTask(cyclicBarrier);

            executorService.execute(readyAttachDateTask);

        }

        executorService.shutdown();








    }
}
