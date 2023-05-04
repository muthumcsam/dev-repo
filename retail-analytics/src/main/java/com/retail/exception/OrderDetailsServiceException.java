package com.retail.exception;

public class OrderDetailsServiceException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1844208336834071265L;
	public OrderDetailsServiceException() {
        super();
    }
    public OrderDetailsServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public OrderDetailsServiceException(String message) {
        super(message);
    }
    public OrderDetailsServiceException(Throwable cause) {
        super(cause);
    }
}
