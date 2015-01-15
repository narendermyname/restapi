/**
 * 
 */
package com.interview.task.service;

import java.util.List;

import com.interview.task.dto.Task;
import com.interview.task.exception.TaskException;

/**
 * This is service interface
 * @author narender
 *
 */
public interface Service<T> {
	/**
	 * List all task 
	 * @return
	 */
	List<T> getTaskList();
	/**
	 * Search task
	 * @param name
	 * @param status
	 * @param priority
	 * @return
	 */
	List<T> searchTask(Task task);
	/**
	 * Add task 
	 * @param task
	 */
	void saveTask(T task);
	/**
	 * Update task
	 * @param task
	 * @return
	 */
	T updateTask(T task);
	/**
	 * delete task based on name
	 * @param task
	 */
	void deleteTask(Task task);
	/**
	 * Save task to disk
	 * @param  List<task>
	 * @return
	 * @throws TaskException 
	 */
	String saveTaskToDisk() throws TaskException;

}
