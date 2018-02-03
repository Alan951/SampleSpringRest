package simplerestapp.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import simplerestapp.handlers.ErrorMessage;;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
		
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseBody
	protected ResponseEntity<ErrorMessage> handleEntityNotFound(EntityNotFoundException ex){
		ErrorMessage ae = new ErrorMessage();
		
		ae.setStatus(HttpStatus.NOT_FOUND);
		ae.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(ae, ae.getStatus());
	}
	
	private ResponseEntity<Object> buildResponseEntity(ErrorMessage apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
