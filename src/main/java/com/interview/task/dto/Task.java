/**
 * 
 */
package com.interview.task.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author narender
 *
 */
@XmlRootElement(name="task")
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3248539310988951962L;

	private String name;
	
	private String status;
	
	private String priority;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "Task [name=" + name + ", status=" + status + ", priority="
				+ priority + "]";
	}
	
	
}
