package com.test.FutrueModel;

import java.util.List;

/**
 * @Description: 查询数据的包装类 这个实现类 用到了线程的wait和notify配合synchronized
 * 导致线程进入等待状态直到它被通知或者经过指定的时间。这些方法只能在同步方法中调用。
 * 如果当前线程不是锁的持有者，该方法抛出一个IllegalMonitorStateException异常。
Object.wait()和Object.notify()和Object.notifyall()
必须写在synchronized方法内部或者synchronized块内部，
这是因为：这几个方法要求当前正在运行object.wait()方法的线程拥有object的对象锁
 * @Author：pengrj
 * @Date : 2018/10/15 0015 21:12
 * @version:1.0
 */
public class FutrueDate implements  Date<List,String> {

     private   RealDate realDate;

     //数据是否处理完毕标识
     private   Boolean isReady=false;

     /*数据包装类加载加载真实的数据信息 供数据获取客户端使用*/
     public synchronized void setRealDate(RealDate realDate){

                  //数据加载完毕直接返回
                  if(isReady){
                      return;
                  }

                  //如果没有加载完毕 进行数据的加载
                  this.realDate=realDate;

         isReady=true;

         //进行数据获取的通知
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
