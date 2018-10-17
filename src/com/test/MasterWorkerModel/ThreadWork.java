package com.test.MasterWorkerModel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  处理任务的工作线程
 * @Author：pengrj
 * @Date : 2018/10/17 0017 21:16
 * @version:1.0
 */
public class ThreadWork  implements  Runnable{


    private  ConcurrentLinkedQueue<Task> concurrentLinkedQueue;

    private  ConcurrentHashMap<String, Object> resultMap;



    //每个工作的线程Work都需要有Master里面公共的任务集合及处理结果集合 用户Work从任务集合中取元素及存放处理结果

    public void setConcurrentLinkedQueue(ConcurrentLinkedQueue<Task> concurrentLinkedQueue) {

            this.concurrentLinkedQueue=concurrentLinkedQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
           this.resultMap=resultMap;
    }





    //从任务队列中取任务进行处理并将处理结果放在结果集中中
    @Override
    public void run() {

        while (true) {
            Task task=this.concurrentLinkedQueue.poll();
            if(task==null) {

                break;
            }


            //处理数据返回结果
            Object res=handlerTask(task);
            //将处理的结果存入在结果集中
            this.resultMap.put(Integer.toString(task.getId()),res);
        }

    }

    //这个handler方法可以新建Work对象进行重新 采用不同的处理方法
    private Object handlerTask(Task task) {

        Object res=null;

        try {
            //每个任务的处理时长为0.1s
            Thread.sleep(100);
            //返回任务的预定处理时间
            res=task.getTaskTime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return  res;
    }



}
