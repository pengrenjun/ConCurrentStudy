package com.test.FutrueModel;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: ��ʵ�����ݶ����ѯ��Ϣ
 * @Author��pengrj
 * @Date : 2018/10/15 0015 20:57
 * @version:1.0
 */
public class RealDate implements Date<List,String> {

    private  String requstParam;

    private  static List<String> list ;


    //ͨ�����캯�� �����µ��߳̽������ݵĲ�ѯ
    public RealDate(String requstParam) {
        this.requstParam = requstParam;

        System.out.println("����Ĳ���requstParam: "+requstParam+" ��̨���̿�ʼ�������ݵĲ�ѯ  ����������");

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("��ѯ����OK>>>>>");
        list= Arrays.asList("a","b","c");
    }

    @Override
    public List getRequest(String requstParam) {
        return list;
    }
}
