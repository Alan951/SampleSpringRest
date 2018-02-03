package simplerestapp.handlers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import simplerestapp.util.JsonLocalDateTimeSerializer;

public class ErrorMessage {
	private HttpStatus status;
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> errors;
	
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime timestamp;
	
	public ErrorMessage() {
		this.timestamp = LocalDateTime.now();
	}
	
	public ErrorMessage(HttpStatus status, String message, List<String> errors) {
		this();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
	public ErrorMessage(HttpStatus status, String message, String errors) {
		this();
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(errors);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
