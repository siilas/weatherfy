package com.github.siilas.weatherfy.openweathermap.deserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) 
			throws IOException, JsonProcessingException {
		long value = parser.getLongValue();
		return LocalDateTime.ofEpochSecond(value, 0, ZoneOffset.UTC);
	}

}
