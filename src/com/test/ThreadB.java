package com.test;

/**
 * @Description:    synchronized关键字在类中的用法理解
 * @Author：pengrj
 * @Date : 2018/9/27 0027 20:44
 * @version:1.0
 */
public class ThreadB {

    private static  int count=0;

    /**printNum 方法单独加上synchronized的输出结果：
     qwe->set count ok !->1
     qw->set count ok !->2
     qw->set count ok !->2
     qwe->set count ok !->2*/

    public static synchronized void   printNum(String str) throws InterruptedException {

        if(str.length()>2){
            count=++count;

            System.out.println(str+"->set count ok !->"+count);

            Thread.sleep(1000);
        }

        else {
            count=++count;

            System.out.println(str+"->set count ok !->"+count);
        }


        System.out.println(str+"->set count ok !->"+count);
    }


    public static void main(String[] args) {

        //创建两个不同的类对象
        ThreadB threadA=new ThreadB();
        ThreadB threadB=new ThreadB();

        //两个线程对象各持有ThreadB类中方法printNum的锁 两者互不干扰
        //如果想对ThreadB类中方法printNum方法对不同的线程有效 需要把方法设为类方法 这样这个方法就是类持有的

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    threadA.printNum("qwe");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    threadB.printNum("qw");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
