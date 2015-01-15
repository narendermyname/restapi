/**
 * 
 */
package com.interview.task.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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

/**
 * @author narender
 *
 */
@Path("/task")
public class TaskController {

	private static List<Task> taskList=new ArrayList<Task>();
	private static final Logger LOGGER=Logger.getLogger(TaskController.class);
	private static String TASK_FILE=System.getProperty("java.io.tmpdir")+"/taskList.txt";
	/**
	 * This method is use to get list of all tasks
	 * @return
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings({ "resource", "unchecked" })
	@GET
	//@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getTaskList() throws Exception{
		LOGGER.debug("Start TaskController.getTaskList()");
		if(taskList==null){
			FileInputStream fout;
			ObjectInputStream oos;
			LOGGER.info("FIle where we store tasks : "+TASK_FILE);
			try {
				fout = new FileInputStream(TASK_FILE);
				oos = new ObjectInputStream(fout);
				taskList=(List<Task>) oos.readObject();
			} catch (final Exception e) {
				LOGGER.error("Error while saving tasks to disk : ", e);
				taskList=new ArrayList<Task>();
			} 
		}
		LOGGER.debug("Ends TaskController.getTaskList()");
		return taskList;
	}
	/**
	 * This method is use to get list of all tasks
	 * @return
	 */
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> searchTask(@QueryParam("name") String name,@QueryParam("status")String status,@QueryParam("priority")String priority){
		LOGGER.debug("Start TaskController.searchTask()");
		List<Task> responseList=new ArrayList<Task>();
		for(Task task:taskList){
			if((name!=null && name.equals(task.getName())) ||
					(status!=null && status.equals(task.getStatus())) ||
					(priority!=null && priority.equals(task.getPriority()))){
				responseList.add(task);
			}
		}
		return responseList;
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
		LOGGER.debug("Ends TaskController.addTask() added task : "+task);
		taskList.add(task);
	}
	/**
	 * This method is use to add task using POST method
	 * @param task
	 */
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Task addTaskPost(Task task){
		LOGGER.debug("Start TaskController.addTask()");
		taskList.add(task);
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
	//@Produces(MediaType.APPLICATION_JSON)
	public Task updateTask(Task task){
		LOGGER.debug("Start TaskController.updateTask() update request : "+task);
		Task temp=null;
		for(Task t:taskList){
			if(task.getName().equals(t.getName())){
				temp=t;
				taskList.remove(t);
				temp.setPriority(task.getPriority());
				temp.setStatus(task.getStatus());
				taskList.add(temp);
			}
		}
		if(temp==null){
			temp=new Task();
		}
		LOGGER.debug("Ends TaskController.updateTask() updated task "+temp);
		return temp;
	}
	/**
	 * Delete task from task List
	 * @param name
	 */
	@DELETE
	@Path("/delete/{name}")
	public Task deleteTask(@PathParam("name")String name){
		LOGGER.debug("Starst TaskController.delete task name to delete : "+name);
		List<Task> tempList=new ArrayList<Task>();
		Task tempTask=null;
		for(Task task:taskList){
			if(task.getName().equals(name)){
				tempList.add(task);
				tempTask=task;
			}
		}
		if(!tempList.isEmpty()){
			taskList.removeAll(tempList);
			return tempList.get(0);
		}
		LOGGER.debug("Ends TaskController.deleteTask() taskList after detele task +");
		return tempTask;
	}
	/**
	 * Save tasks to disk
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@GET
	@Path("saveTaskToDisk")
	public String saveTaskToDisk() throws Exception{
		LOGGER.debug("Starst TaskController.saveTaskToDisk()");
		FileOutputStream fout;
		ObjectOutputStream oos;
		LOGGER.info("FIle where we store tasks : "+TASK_FILE);
		try {
			fout = new FileOutputStream(TASK_FILE);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(taskList);
		} catch (IOException e) {
			LOGGER.error("Error while saving tasks to disk : ", e);
			return "ERROR";
		} 
		LOGGER.debug("Ends TaskController.saveTaskToDisk()");
		return "SUCCESS";
	}
}
