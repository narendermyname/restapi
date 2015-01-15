/**
 * 
 */
package com.interview.task.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.task.dto.Task;
import com.interview.task.exception.TaskException;
import com.interview.task.repository.TaskRepository;
import com.interview.task.repository.impl.TaskRepositoryImpl;
import com.interview.task.service.TaskService;

/**
 * This is service layer service class having task events
 * @author narender
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

	private static final Logger LOG=Logger.getLogger(TaskRepositoryImpl.class);
	@Autowired
	private TaskRepository taskRepository;
	@Override
	public List<Task> getTaskList() {
		LOG.debug("Starts TaskRepositoryImpl.getTaskList()");
		List<Task> taskList=taskRepository.list();
		LOG.debug("Starts TaskRepositoryImpl.getTaskListO()");
		return taskList;
	}

	@Override
	public List<Task> searchTask(Task task) {
		LOG.debug("Starts TaskRepositoryImpl.searchTask()");
		List<Task> taskList=taskRepository.search(task);
		LOG.debug("Starts TaskRepositoryImpl.searchTask()");
		return taskList;
	}

	@Override
	public void saveTask(Task task) {
		LOG.debug("Starts TaskRepositoryImpl.addTaskPost()");
		taskRepository.save(task);
		LOG.debug("Starts TaskRepositoryImpl.addTaskPost()");
	}

	@Override
	public Task updateTask(Task task) {
		LOG.debug("Starts TaskRepositoryImpl.updateTask()");
		Task response=taskRepository.update(task);
		LOG.debug("Starts TaskRepositoryImpl.updateTask()");
		return response;
		
	}

	@Override
	public void deleteTask(Task task) {
		LOG.debug("Starts TaskRepositoryImpl.deleteTask()");
		taskRepository.delete(task);
		LOG.debug("Starts TaskRepositoryImpl.deleteTask()");
	}

	@Override
	public String saveTaskToDisk() throws TaskException {
		LOG.debug("Starts TaskRepositoryImpl.saveTaskToDisk()");
		String  response=taskRepository.saveTaskToDisk();
		LOG.debug("Starts TaskRepositoryImpl.saveTaskToDisk()");
		return response;
	}



}
