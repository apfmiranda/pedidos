package com.apfmiranda.backendpedidos.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.apfmiranda.backendpedidos.services.exceptions.AuthorizationException;
import com.apfmiranda.backendpedidos.services.exceptions.DataIntegrityException;
import com.apfmiranda.backendpedidos.services.exceptions.FileException;
import com.apfmiranda.backendpedidos.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourcesExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value(), 
				"ObjectNotFoundException",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);		
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), 
				"DataIntegrityException",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError(
				System.currentTimeMillis(),
				HttpStatus.UNPROCESSABLE_ENTITY.value(), 
				"MethodArgumentNotValidException",
				e.getMessage(),
				request.getRequestURI());
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);		
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.FORBIDDEN.value(), 
				"AuthorizationException",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);		
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){
		
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), 
				"FileException",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);		
	}
	
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request){
		
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				code.value(), 
				code.name(),
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(code).body(err);		
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request){
		
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), 
				"AmazonClientException",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);		
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request){
		
		StandardError err = new StandardError(
				System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), 
				"AmazonS3Exception",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);		
	}
	

}
