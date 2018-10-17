package com.test.FutrueModel;

import java.util.List;

/**
 * @Description: 测试
 * @Author：pengrj
 * @Date : 2018/10/15 0015 21:41
 * @version:1.0
 */
public class Test {

    public static void main(String[] args) {

        FutrueClient futrueClient=new FutrueClient();

        Date data = futrueClient.request("模拟参数abc");

        System.out.println("数据请求完毕,可以进行其他业务的处理");

        //获取真实处理后的数据
        List<String> list=(List<String>)data.getRequest("模拟参数abc");

        System.out.println(list.toString());



    }
}
