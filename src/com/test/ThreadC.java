package com.test;

/**
 * @Description:   ���ȷ�����ݸ�ֵ���ȡ��ֵ��ͳһ
 * ��ȡͬʱ���� ��֤����ͳһ��
 * @Author��pengrj
 * @Date : 2018/9/27 0027 21:25
 * @version:1.0
 */
public class ThreadC {

    private   String name="xiaoming";
    private   int password=123456789;

    private  synchronized   void setValue(){


        System.out.println("�����û�������������");

        try {
            Thread.sleep(2000);

            name="xiaoqiang";
            password=122626;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("�û��������������:name ,password  "+name+" ,"+password);
    }

    private synchronized void getValue(){
        System.out.println("��ȡ���ĵ�ǰ���û���������:"+name+"  "+password);
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
