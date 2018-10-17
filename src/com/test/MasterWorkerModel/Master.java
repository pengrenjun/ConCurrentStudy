package com.test.MasterWorkerModel;

import com.sun.istack.internal.NotNull;
import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Description: ���ܺͷ������� �����ض��̴߳���Ľ������
 * @Author��pengrj
 * @Date : 2018/10/17 0017 20:53
 * @version:1.0
 */
public class Master {

    //��������Ķ��м���
    private ConcurrentLinkedQueue<Task> concurrentLinkedQueue=new ConcurrentLinkedQueue<>();

    //װ�����е�Worker���� WORK������Ҫʵ��Runnable�ӿ�
    private HashMap<String,Thread> works=new HashMap<>();

    //װ�ض��̲߳��� Works�Ĵ���������
    private ConcurrentHashMap<String,Object> resultMap=new ConcurrentHashMap<>();


    //���췽�� ��works����ʵ���� ȷ���������������̴߳�������
    public Master(ThreadWork threadWork,final int worksCount) {

        for(int i=0;i<worksCount;i++){

            //ÿ�������������߳���Ҫ�����񼯺϶��м������ ����Master��������Ĵ�������Ĺ��ɻ���
            threadWork.setConcurrentLinkedQueue(this.concurrentLinkedQueue);
            threadWork.setResultMap(this.resultMap);
            works.put(Integer.toString(threadWork.hashCode())+":"+i,new Thread(threadWork));
        }

    }

    //Master�����񼯺϶�����װ����������
    public void loadTasks(@NotNull  Task task){
        this.concurrentLinkedQueue.add(task);
    }

    //Master�������еĹ����߳�������������
    public void execute(){
        for (Map.Entry<String,Thread> threadEntry:this.works.entrySet()){
            threadEntry.getValue().start();
        }
    }

    //���еĹ��������Ƿ��Ѿ��������
    public Boolean worksHasProcessed(){

        for (Map.Entry<String,Thread> threadEntry:this.works.entrySet()){
            if(threadEntry.getValue().getState()!=Thread.State.TERMINATED){
                return  false;
            }
        }
        return  true;
    }

    //�Խ�����д��� ��ȡ����Ľ��
    public Object getHandlerResult(){
        Long res=0L;
        //�����й��������Ԥ��ʱ���������
        for(Map.Entry<String,Object> entry:this.resultMap.entrySet()){

            res = (Long) (res + (Long) entry.getValue());
        }
        return  res;
    }
}
