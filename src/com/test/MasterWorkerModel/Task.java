package com.test.MasterWorkerModel;

/**
 * @Description: 任务对象
 * @Author：pengrj
 * @Date : 2018/10/17 0017 20:55
 * @version:1.0
 */
public class Task {

    //任务id
    private Integer id;

    //任务名称
    private String  taskName;

    //任务的预定处理时间
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
