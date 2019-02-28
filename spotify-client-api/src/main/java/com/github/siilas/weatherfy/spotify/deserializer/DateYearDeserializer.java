package com.github.siilas.weatherfy.spotify.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateYearDeserializer extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        String date = parser.getValueAsString();
        return getYear(date);
    }
    
    public Integer getYear(String date) {
        return Integer.parseInt(date.substring(0, 4));
    }

}
