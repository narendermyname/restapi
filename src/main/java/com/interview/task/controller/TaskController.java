/**
 * 
 */
package com.interview.task.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.interview.task.dto.Task;
import com.interview.task.dto.Tasks;
import com.interview.task.spring.context.TaskAppContext;

/**
 * @author narender
 *
 */
@Path("/task")
public class TaskController {

	private static final Logger LOGGER=Logger.getLogger(TaskController.class);
	
	/**
	 * This method is use to get list of all tasks
	 * @return
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	@GET
	//@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Tasks getTaskList() throws Exception{
		LOGGER.debug("Start TaskController.getTaskList()");
		Tasks tasks=new Tasks();
		List<Task> list=TaskAppContext.getInstance().getTaskService().getTaskList();
		tasks.setTaskList(list);
		LOGGER.debug("Ends TaskController.getTaskList()");
		return tasks;
	}
	/**
	 * This method is use to get list of all tasks
	 * @return
	 */
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Tasks searchTask(@QueryParam("name") String name,@QueryParam("status")String status,@QueryParam("priority")String priority){
		LOGGER.debug("Start TaskController.searchTask()");
		Task task =new Task();
		task.setName(name);
		task.setStatus(status);
		task.setPriority(priority);
		Tasks tasks=new Tasks();
		List<Task> responseList=TaskAppContext.getInstance().getTaskService().searchTask(task);
		tasks.setTaskList(responseList);
		LOGGER.debug("TaskController.searchTask() end.");
		return tasks;
	}
	/**
	 * This method is use to add task using Get method 
	 * @param name
	 * @param status
	 * @param priority
	 */
	@GET
	@Path("/add")
	public void addTask(@QueryParam("name") String name,@QueryParam("status")String status,@QueryParam("priority")String priority){
		LOGGER.debug("Start TaskController.addTask()");
		Task task=new Task();
		task.setName(name);
		task.setStatus(status);
		task.setPriority(priority);
		TaskAppContext.getInstance().getTaskService().saveTask(task);
		LOGGER.debug("Ends TaskController.addTask() added task : "+task);
	}
	/**
	 * This method is use to add task using POST method
	 * @param task
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Task addTaskPost(Task task){
		LOGGER.debug("Start TaskController.addTask()");
		TaskAppContext.getInstance().getTaskService().saveTask(task);
		LOGGER.debug("Ends TaskController.addTask() added task : "+task);
		return task;
	}
	/**
	 * This method is use to update task 
	 * @param task
	 */
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Task updateTask(Task task){
		LOGGER.debug("Start TaskController.updateTask() update request : "+task);
		Task updatedTask=TaskAppContext.getInstance().getTaskService().updateTask(task);
		LOGGER.debug("Ends TaskController.updateTask() updated task "+updatedTask);
		return updatedTask;
	}
	/**
	 * Delete task from task List
	 * @param name
	 */
	@DELETE
	@Path("/delete/{name}")
	public void deleteTask(@PathParam("name")String name){
		LOGGER.debug("Starst TaskController.delete task name to delete : "+name);
		Task task=new Task();
		task.setName(name);
		TaskAppContext.getInstance().getTaskService().deleteTask(task);
		LOGGER.debug("Ends TaskController.deleteTask() taskList after detele task ");
	}
	/**
	 * Save tasks to disk
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("saveTaskToDisk")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveTaskToDisk() throws Exception{
		LOGGER.debug("Starst TaskController.saveTaskToDisk()");
		TaskAppContext.getInstance().getTaskService().saveTaskToDisk();
		LOGGER.debug("Ends TaskController.saveTaskToDisk()");
		return "SUCCESS";
	}
}
