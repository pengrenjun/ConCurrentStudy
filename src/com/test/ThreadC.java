package com.test;

/**
 * @Description:   如何确保数据赋值与读取的值的统一
 * 读取同时加锁 保证数据统一性
 * @Author：pengrj
 * @Date : 2018/9/27 0027 21:25
 * @version:1.0
 */
public class ThreadC {

    private   String name="xiaoming";
    private   int password=123456789;

    private  synchronized   void setValue(){


        System.out.println("启动用户名和密码设置");

        try {
            Thread.sleep(2000);

            name="xiaoqiang";
            password=122626;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("用户名密码设置完毕:name ,password  "+name+" ,"+password);
    }

    private synchronized void getValue(){
        System.out.println("获取到的当前的用户名和密码:"+name+"  "+password);
    }


    public static void main(String[] args) throws InterruptedException {

        ThreadC  threadC=new ThreadC();


        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                threadC.setValue();
            }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                threadC.getValue();
            }
        });


        thread1.start();

        Thread.sleep(1000);

        thread2.start();

    }
 }
