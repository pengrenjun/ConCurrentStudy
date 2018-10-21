package com.test.Lock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:  �������ļ�Ӧ�� ʵ�ֹ�����synchronized������ͬ ʵ���߳�ͬ��
 * @Author��pengrj
 * @Date : 2018/10/20 0020 21:26
 * @version:1.0
 */
public class UseReentrantLock {

    private Lock lock=new ReentrantLock();

    public List<String> list=new ArrayList<>();

    public void setList(List<String> list) {
        lock.lock();
        System.out.println("����setList���� ΪList���и�ֵ��������������������");

        try {
            TimeUnit.SECONDS.sleep(5);
            this.list = list;
            System.out.println("List���ݳ�ʼ��OK!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public List<String> getList() {
        lock.lock();
        System.out.println("��ȡlist����");
        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("��ȡlist����OK>>>>>>>>>>>>>>>>");
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
