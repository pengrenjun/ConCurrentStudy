package com.test;

/**
 * @Description:    synchronized�ؼ��������е��÷����
 * @Author��pengrj
 * @Date : 2018/9/27 0027 20:44
 * @version:1.0
 */
public class ThreadB {

    private static  int count=0;

    /**printNum ������������synchronized����������
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

        //����������ͬ�������
        ThreadB threadA=new ThreadB();
        ThreadB threadB=new ThreadB();

        //�����̶߳��������ThreadB���з���printNum���� ���߻�������
        //������ThreadB���з���printNum�����Բ�ͬ���߳���Ч ��Ҫ�ѷ�����Ϊ�෽�� �������������������е�

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
