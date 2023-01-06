package com.masai.main.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MyRuntimeExceptions.class)
	public ResponseEntity<MyErrorDetails> firstException(MyRuntimeExceptions e,WebRequest w){
		MyErrorDetails err=new MyErrorDetails();
		err.setDateTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(w.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> secondException(Exception e,WebRequest w){
		MyErrorDetails err=new MyErrorDetails();
		err.setDateTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(w.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> thirdException(NoHandlerFoundException e,WebRequest w){
		MyErrorDetails err=new MyErrorDetails();
		err.setDateTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(w.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> fourthException(MethodArgumentNotValidException e){
		MyErrorDetails err=new MyErrorDetails();
		err.setDateTime(LocalDateTime.now());
		err.setMessage("Validation Error");
		err.setDetails(e.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
}
