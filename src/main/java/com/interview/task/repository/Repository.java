/**
 * 
 */
package com.interview.task.repository;

import java.util.List;

import com.interview.task.dto.Task;
import com.interview.task.exception.TaskException;

/**
 * @author narender
 *
 */
public interface Repository<T> {
	/**
	 * List all task 
	 * @return
	 */
	List<T> list();
	/**
	 * Search task
	 * @param name
	 * @param status
	 * @param priority
	 * @return
	 */
	List<T> search(Task task);
	/**
	 * Add task 
	 * @param task
	 */
	void save(T task);
	/**
	 * Update task
	 * @param task
	 * @return
	 */
	T update(T task);
	/**
	 * delete task based on name
	 * @param task
	 */
	void delete(Task task);
	/**
	 * Save task to disk
	 * @return
	 * @param List<Task>
	 * @throws TaskException 
	 */
	String saveTaskToDisk() throws TaskException;
}
