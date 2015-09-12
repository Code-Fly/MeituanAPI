/**
 * 
 */
package com.base.exception;

/**
 * @author Administrator
 *
 */
public class ApiControllerException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7699659238816417033L;

	public ApiControllerException(String message) {
		super(message);
	}

	public ApiControllerException(String paramString, Throwable paramThrowable) {
		super(paramString, paramThrowable);
	}
}
