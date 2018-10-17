package com.test.FutrueModel;

import java.util.List;

/**
 * @Description:  数据获取客户端
 * @Author：pengrj
 * @Date : 2018/10/15 0015 21:35
 * @version:1.0
 */
public class FutrueClient  {

    /*获取包装数据同时开启新的线程查询异步数据*/
    public Date request(final String requestParam){

        final  FutrueDate futrueDate=new FutrueDate();

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //处理大量耗时的数据
                RealDate realDate=new RealDate(requestParam);
                futrueDate.setRealDate(realDate);
            }
        });

         thread.start();

         return futrueDate;
    }
}
