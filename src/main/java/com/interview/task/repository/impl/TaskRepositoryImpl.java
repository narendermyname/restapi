/**
 * 
 */
package com.interview.task.repository.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.interview.task.dto.Task;
import com.interview.task.exception.TaskException;
import com.interview.task.repository.TaskRepository;

/**
 * THis is Task Repository class 
 * @author narender
 *
 */
@Repository
public class TaskRepositoryImpl implements TaskRepository {

	private static final String TASK_FILE=System.getProperty("java.io.tmpdir")+"/taskList.txt";
	private static final Logger LOG=Logger.getLogger(TaskRepositoryImpl.class);
	private static List<Task> taskList=new ArrayList<Task>();
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> list() {
		LOG.debug("Starts TaskRepositoryImpl.list()");
		if(taskList.isEmpty()){
			FileInputStream fout;
			ObjectInputStream oos;
			LOG.info("FIle where we store tasks : "+TASK_FILE);
			try {
				fout = new FileInputStream(TASK_FILE);
				oos = new ObjectInputStream(fout);
				taskList=(List<Task>) oos.readObject();
			} catch (final Exception e) {
				LOG.error("Error while saving tasks to disk : ", e);
				taskList=new ArrayList<Task>();
			} 
		}
		LOG.debug("Ends TaskRepositoryImpl.list()");
		return taskList;
	}

	@Override
	public List<Task> search(Task task) {	
		LOG.debug("Starts TaskRepositoryImpl.search() task :"+task);
		List<Task> responseList=new ArrayList<Task>();
		for(Task t:taskList){
			if((task.getName()!=null && task.getName().equals(t.getName())) ||
					(task.getStatus()!=null && task.getStatus().equals(t.getStatus())) ||
					(task.getPriority()!=null && task.getPriority().equals(t.getPriority()))){
				responseList.add(t);
			}
		}
		LOG.debug("Ends TaskRepositoryImpl.search()");
		return responseList;
	}

	@Override
	public void save(Task task) {
		LOG.debug("Starts TaskRepositoryImpl.save() task :"+task);
		taskList.add(task);
		LOG.debug("Ends TaskRepositoryImpl.save()");
	}

	@Override
	public Task update(Task task) {
		LOG.debug("Starts TaskRepositoryImpl.update() task: "+task);
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
		LOG.debug("Ends TaskRepositoryImpl.update()");		
		return temp;
	}

	@Override
	public void delete(Task task) {
		LOG.debug("Starts TaskRepositoryImpl.delete() task :"+task);
		List<Task> temp=new ArrayList<Task>();
		for(Task t:taskList){
			if(t.getName().equals(task.getName())){
				temp.add(t);
			}
		}
		for(Task t:temp){
			taskList.remove(t);
		}
		LOG.debug("Ends TaskRepositoryImpl.delete()");		
	}

	@SuppressWarnings("resource")
	@Override
	public String saveTaskToDisk() throws TaskException {
		LOG.debug("Starts TaskRepositoryImpl.saveTaskToDisk()taskList :  "+taskList);
		FileOutputStream fout;
		ObjectOutputStream oos;
		String response="SUCCESS";
		LOG.info("FIle where we store tasks : "+TASK_FILE);
		try {
			fout = new FileOutputStream(TASK_FILE);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(taskList);
		} catch (IOException e) {
			LOG.error("Error while saving tasks to disk : ", e);
			response="ERROR";
			throw new TaskException("ERROR while save data to disk");
		} 
		LOG.debug("Ends TaskRepositoryImpl.saveTaskToDisk()");
		return response;
	}


}
