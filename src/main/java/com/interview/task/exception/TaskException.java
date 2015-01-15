/**
 * 
 */
package com.interview.task.exception;

/**
 * Custom Task Exception
 * @author narender
 *
 */
public class TaskException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4988014512875802527L;

	/**
	 * 
	 */
	public TaskException() {
	}

	/**
	 * @param message
	 */
	public TaskException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TaskException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TaskException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TaskException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
