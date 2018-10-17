package com.test.MasterWorkerModel;

import java.util.Random;

/**
 * @Description: ģ��ͻ��˲��� MasterWork��������ģʽ
 * @Author��pengrj
 * @Date : 2018/10/17 0017 22:09
 * @version:1.0
 */
public class Test {

    public static void main(String[] args) {

        System.out.println("���õ�����߳�����"+Runtime.getRuntime().availableProcessors());

        Master master=new Master(new ThreadWork(),Runtime.getRuntime().availableProcessors());

        Random random=new Random();
        //���1000����������
        for(int i=0;i<3;i++){
            Task task=new Task();
            task.setId(i);
            task.setTaskName("����"+i);
            task.setTaskTime(Long.valueOf(random.nextInt(10)));

            master.loadTasks(task);
        }

        //������������
        master.execute();

        long st = System.currentTimeMillis();

        //���еĹ����̴߳������֮�� ��ӡ����Ľ��
        while (true){

            if(master.worksHasProcessed()){

                long ed= System.currentTimeMillis();

                //������� ��ӡ���
                Object res=master.getHandlerResult();

                System.out.println("���������Ԥ��ʱ����"+res+" �̴߳����ʱ"+(ed-st));

                break;

            }



        }
    }
}
