package com.test.MasterWorkerModel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  ��������Ĺ����߳�
 * @Author��pengrj
 * @Date : 2018/10/17 0017 21:16
 * @version:1.0
 */
public class ThreadWork  implements  Runnable{


    private  ConcurrentLinkedQueue<Task> concurrentLinkedQueue;

    private  ConcurrentHashMap<String, Object> resultMap;



    //ÿ���������߳�Work����Ҫ��Master���湫�������񼯺ϼ����������� �û�Work�����񼯺���ȡԪ�ؼ���Ŵ�����

    public void setConcurrentLinkedQueue(ConcurrentLinkedQueue<Task> concurrentLinkedQueue) {

            this.concurrentLinkedQueue=concurrentLinkedQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
           this.resultMap=resultMap;
    }





    //�����������ȡ������д��������������ڽ��������
    @Override
    public void run() {

        while (true) {
            Task task=this.concurrentLinkedQueue.poll();
            if(task==null) {

                break;
            }


            //�������ݷ��ؽ��
            Object res=handlerTask(task);
            //������Ľ�������ڽ������
            this.resultMap.put(Integer.toString(task.getId()),res);
        }

    }

    //���handler���������½�Work����������� ���ò�ͬ�Ĵ�����
    private Object handlerTask(Task task) {

        Object res=null;

        try {
            //ÿ������Ĵ���ʱ��Ϊ0.1s
            Thread.sleep(100);
            //���������Ԥ������ʱ��
            res=task.getTaskTime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return  res;
    }



}
