package com.test.MasterWorkerModel;

/**
 * @Description: �������
 * @Author��pengrj
 * @Date : 2018/10/17 0017 20:55
 * @version:1.0
 */
public class Task {

    //����id
    private Integer id;

    //��������
    private String  taskName;

    //�����Ԥ������ʱ��
    private Long    taskTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Long taskTime) {
        this.taskTime = taskTime;
    }
}
