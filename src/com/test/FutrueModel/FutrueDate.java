package com.test.FutrueModel;

import java.util.List;

/**
 * @Description: ��ѯ���ݵİ�װ�� ���ʵ���� �õ����̵߳�wait��notify���synchronized
 * �����߳̽���ȴ�״ֱ̬������֪ͨ���߾���ָ����ʱ�䡣��Щ����ֻ����ͬ�������е��á�
 * �����ǰ�̲߳������ĳ����ߣ��÷����׳�һ��IllegalMonitorStateException�쳣��
Object.wait()��Object.notify()��Object.notifyall()
����д��synchronized�����ڲ�����synchronized���ڲ���
������Ϊ���⼸������Ҫ��ǰ��������object.wait()�������߳�ӵ��object�Ķ�����
 * @Author��pengrj
 * @Date : 2018/10/15 0015 21:12
 * @version:1.0
 */
public class FutrueDate implements  Date<List,String> {

     private   RealDate realDate;

     //�����Ƿ�����ϱ�ʶ
     private   Boolean isReady=false;

     /*���ݰ�װ����ؼ�����ʵ��������Ϣ �����ݻ�ȡ�ͻ���ʹ��*/
     public synchronized void setRealDate(RealDate realDate){

                  //���ݼ������ֱ�ӷ���
                  if(isReady){
                      return;
                  }

                  //���û�м������ �������ݵļ���
                  this.realDate=realDate;

         isReady=true;

         //�������ݻ�ȡ��֪ͨ
         notify();
     }

    @Override
    public  synchronized List getRequest(String requstParam) {
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this.realDate.getRequest(requstParam);
    }
}
