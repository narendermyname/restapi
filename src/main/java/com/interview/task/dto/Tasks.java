/**
 * 
 */
package com.interview.task.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author narender
 *
 */
@XmlRootElement(name="tasks")
public class Tasks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1068908784242417851L;
	private List<Task> taskList;

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return "Tasks []";
	}
	
	
}
