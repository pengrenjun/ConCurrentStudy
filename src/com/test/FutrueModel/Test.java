package com.test.FutrueModel;

import java.util.List;

/**
 * @Description: ����
 * @Author��pengrj
 * @Date : 2018/10/15 0015 21:41
 * @version:1.0
 */
public class Test {

    public static void main(String[] args) {

        FutrueClient futrueClient=new FutrueClient();

        Date data = futrueClient.request("ģ�����abc");

        System.out.println("�����������,���Խ�������ҵ��Ĵ���");

        //��ȡ��ʵ����������
        List<String> list=(List<String>)data.getRequest("ģ�����abc");

        System.out.println(list.toString());



    }
}
