package com.lp.exception;

public class InsufficientAmountException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public InsufficientAmountException() {}
	
	public InsufficientAmountException(String s) {
		
		super(s);
	}
}
