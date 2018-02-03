package simplerestapp.handlers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import simplerestapp.util.JsonLocalDateTimeSerializer;

public class SuccessfulMessage {
	private HttpStatus status;
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> messages;
	
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime timestamp;
	
	public SuccessfulMessage(){
		status = HttpStatus.OK;
		this.timestamp = LocalDateTime.now();
	}
	
	public SuccessfulMessage(String message) {
		this();
		this.message = message;
	}
	
	public SuccessfulMessage(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}
	
	public SuccessfulMessage(HttpStatus status, String message, List<String> messages) {
		this();
		this.status = status;
		this.message = message;
		this.messages = messages;
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

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
