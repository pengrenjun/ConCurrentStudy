package com.test.FutrueModel;

import java.util.List;

/**
 * @Description:  ���ݻ�ȡ�ͻ���
 * @Author��pengrj
 * @Date : 2018/10/15 0015 21:35
 * @version:1.0
 */
public class FutrueClient  {

    /*��ȡ��װ����ͬʱ�����µ��̲߳�ѯ�첽����*/
    public Date request(final String requestParam){

        final  FutrueDate futrueDate=new FutrueDate();

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //���������ʱ������
                RealDate realDate=new RealDate(requestParam);
                futrueDate.setRealDate(realDate);
            }
        });

         thread.start();

         return futrueDate;
    }
}
