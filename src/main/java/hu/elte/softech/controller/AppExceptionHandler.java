package hu.elte.softech.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import hu.elte.softech.entity.*;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleAppException(Exception ex, WebRequest wr) {

		String emd = ex.getLocalizedMessage();

		if(emd == null) emd = ex.toString();

		ErrorMessage em = new ErrorMessage(new Date(), emd);

		return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not Found!!!")
	public class NotFoundException extends RuntimeException {

	}

}
