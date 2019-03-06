package ca.mcgill.ecse321.backend.controller.advice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler({ AccessDeniedException.class })
	public void handleAccessDeniedException(
			Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.sendError(HttpServletResponse.SC_FORBIDDEN, 
				"Access denied");
//		return new ResponseEntity<Object>(
//				"Access denied", new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	
	@ExceptionHandler({ IllegalArgumentException.class })
	public void handleIllegalArgumentException(
			Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, 
				ex.getMessage());
	}

}
