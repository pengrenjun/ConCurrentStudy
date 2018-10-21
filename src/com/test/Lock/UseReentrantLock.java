package com.test.Lock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:  重入锁的简单应用 实现功能与synchronized基本相同 实现线程同步
 * @Author：pengrj
 * @Date : 2018/10/20 0020 21:26
 * @version:1.0
 */
public class UseReentrantLock {

    private Lock lock=new ReentrantLock();

    public List<String> list=new ArrayList<>();

    public void setList(List<String> list) {
        lock.lock();
        System.out.println("进入setList方法 为List进行赋值》》》》》》》》》》");

        try {
            TimeUnit.SECONDS.sleep(5);
            this.list = list;
            System.out.println("List数据初始化OK!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public List<String> getList() {
        lock.lock();
        System.out.println("获取list数据");
        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("获取list数据OK>>>>>>>>>>>>>>>>");
            System.out.println(list.toString());
            lock.unlock();
            return list;
        }
    }


    public static void main(String[] args) {

        final UseReentrantLock useReentrantLock=new UseReentrantLock();

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantLock.setList(Arrays.asList("q","w","e"));

                useReentrantLock.getList();
            }
        },"t1");

        t1.start();

    }

}
