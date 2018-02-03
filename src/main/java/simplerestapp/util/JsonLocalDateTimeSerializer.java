package simplerestapp.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime>{

	private static final String PATTERN_DATE = "dd-MM-yyyy hh:mm:ss";
	
	@Override
	public void serialize(LocalDateTime date, JsonGenerator jsonGen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		
		final String dateString = date.format(DateTimeFormatter.ofPattern(PATTERN_DATE));
		
		jsonGen.writeString(dateString);
	}

}
