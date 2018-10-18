package com.test;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Description:   简单的线程
 * @Author：pengrj
 * @Date : 2018/9/27 0027 20:31
 * @version:1.0
 */
public class ThreadA  extends  Thread{

    private  static  int  count =1;

    @Override
    public synchronized void run() {

        count++;

        System.out.println(currentThread().getName()+":  "+count);

    }

    public static void main(String[] args) {
        ThreadA threadA=new ThreadA();

        Thread a=new Thread(threadA,"a");
        Thread b=new Thread(threadA,"b");
        Thread c=new Thread(threadA,"c");
        Thread d=new Thread(threadA,"d");
        Thread e=new Thread(threadA,"e");

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();



    }


}


