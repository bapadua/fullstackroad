package br.com.ionic.api.service.exception;

public class MethodNotValidException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MethodNotValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodNotValidException(String message) {
		super(message);
	}
	
	
}
