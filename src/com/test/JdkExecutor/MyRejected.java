package com.test.JdkExecutor;

import java.net.HttpURLConnection;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejected implements RejectedExecutionHandler{

	
	public MyRejected(){
	}
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("自定义处理..");
		System.out.println("当前被拒绝任务为：" + r.toString());


		//实际的项目中 在数据处理高峰期时 如果数据量过大 一般采用两种方式进行处理
		/*a.使用apache的 HttpClient的向客户端发送数据 进行信息提示或者重新请求处理
		*
		* b.将未处理的任务数据 写入工作日志 在通过定时Job在其他时间进行处理
		* */

	}

}
