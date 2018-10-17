package com.test.MasterWorkerModel;

import java.util.Random;

/**
 * @Description: 模拟客户端测试 MasterWork处理数据模式
 * @Author：pengrj
 * @Date : 2018/10/17 0017 22:09
 * @version:1.0
 */
public class Test {

    public static void main(String[] args) {

        System.out.println("可用的最大线程数量"+Runtime.getRuntime().availableProcessors());

        Master master=new Master(new ThreadWork(),Runtime.getRuntime().availableProcessors());

        Random random=new Random();
        //存放1000个任务数据
        for(int i=0;i<3;i++){
            Task task=new Task();
            task.setId(i);
            task.setTaskName("任务"+i);
            task.setTaskTime(Long.valueOf(random.nextInt(10)));

            master.loadTasks(task);
        }

        //启动工作流程
        master.execute();

        long st = System.currentTimeMillis();

        //所有的工作线程处理结束之后 打印处理的结果
        while (true){

            if(master.worksHasProcessed()){

                long ed= System.currentTimeMillis();

                //处理完毕 打印结果
                Object res=master.getHandlerResult();

                System.out.println("所有任务的预计时长："+res+" 线程处理耗时"+(ed-st));

                break;

            }



        }
    }
}
