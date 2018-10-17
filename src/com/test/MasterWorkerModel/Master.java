package com.test.MasterWorkerModel;

import com.sun.istack.internal.NotNull;
import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description: 接受和分配任务 并返回多线程处理的结果程序
 * @Author：pengrj
 * @Date : 2018/10/17 0017 20:53
 * @version:1.0
 */
public class Master {

    //承载任务的队列集合
    private ConcurrentLinkedQueue<Task> concurrentLinkedQueue=new ConcurrentLinkedQueue<>();

    //装载所有的Worker对象 WORK对象需要实现Runnable接口
    private HashMap<String,Thread> works=new HashMap<>();

    //装载多线程并发 Works的处理结果数据
    private ConcurrentHashMap<String,Object> resultMap=new ConcurrentHashMap<>();


    //构造方法 对works进行实例化 确定开启几个工作线程处理任务
    public Master(ThreadWork threadWork,final int worksCount) {

        for(int i=0;i<worksCount;i++){

            //每个开启工作的线程需要绑定任务集合队列及结果集 用来Master进行任务的处理及结果的归纳汇总
            threadWork.setConcurrentLinkedQueue(this.concurrentLinkedQueue);
            threadWork.setResultMap(this.resultMap);
            works.put(Integer.toString(threadWork.hashCode())+":"+i,new Thread(threadWork));
        }

    }

    //Master向任务集合队列中装载任务数据
    public void loadTasks(@NotNull  Task task){
        this.concurrentLinkedQueue.add(task);
    }

    //Master启动所有的工作线程启动处理数据
    public void execute(){
        for (Map.Entry<String,Thread> threadEntry:this.works.entrySet()){
            threadEntry.getValue().start();
        }
    }

    //所有的工作流程是否已经处理完毕
    public Boolean worksHasProcessed(){

        for (Map.Entry<String,Thread> threadEntry:this.works.entrySet()){
            if(threadEntry.getValue().getState()!=Thread.State.TERMINATED){
                return  false;
            }
        }
        return  true;
    }

    //对结果进行处理 获取处理的结果
    public Object getHandlerResult(){
        Long res=0L;
        //对所有工作任务的预计时长进行求和
        for(Map.Entry<String,Object> entry:this.resultMap.entrySet()){

            res = (Long) (res + (Long) entry.getValue());
        }
        return  res;
    }
}
