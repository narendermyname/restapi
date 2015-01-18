/**
 * 
 */
package com.interview.task.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.interview.task.service.TaskService;

/**
 * @author narender
 *
 */
public class TaskAppContext implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	private static TaskAppContext instance=null;
	private static final String TASK_SERVICE="taskService";
	/**
	 * 
	 */
	private TaskAppContext() {
	}

	/**
	 * get TaskAppContext instance
	 * @return
	 */
	public static TaskAppContext getInstance(){
		if(instance == null)
		{
			instance= new TaskAppContext();
		}
		return instance;
	}
	/**
	 * get Task service
	 * @return
	 */
	public TaskService  getTaskService(){
		TaskService taskService=(TaskService) applicationContext.getBean(TASK_SERVICE);
		return taskService;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext =applicationContext;
	}
}
