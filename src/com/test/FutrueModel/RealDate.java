package com.test.FutrueModel;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 真实的数据对象查询信息
 * @Author：pengrj
 * @Date : 2018/10/15 0015 20:57
 * @version:1.0
 */
public class RealDate implements Date<List,String> {

    private  String requstParam;

    private  static List<String> list ;


    //通过构造函数 开启新的线程进行数据的查询
    public RealDate(String requstParam) {
        this.requstParam = requstParam;

        System.out.println("传入的参数requstParam: "+requstParam+" 后台进程开始进行数据的查询  》》》》》");

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("查询数据OK>>>>>");
        list= Arrays.asList("a","b","c");
    }

    @Override
    public List getRequest(String requstParam) {
        return list;
    }
}
