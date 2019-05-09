package com.github.siilas.weatherfy.api.mocks;

public final class OpenWeatherMapMocks {

    private OpenWeatherMapMocks() {
        throw new IllegalStateException();
    }
    
    public static final String SUCCESS_MOCK = "{\n"
            + "   \"coord\": {\n"
            + "      \"lon\": -49.07,\n"
            + "      \"lat\": -22.32\n"
            + "   },\n"
            + "   \"weather\": [\n"
            + "      {\n"
            + "         \"id\": 500,\n"
            + "         \"main\": \"Rain\",\n"
            + "         \"description\": \"light rain\",\n"
            + "         \"icon\": \"10d\"\n"
            + "      },\n"
            + "      {\n"
            + "         \"id\": 701,\n"
            + "         \"main\": \"Mist\",\n"
            + "         \"description\": \"mist\",\n"
            + "         \"icon\": \"50d\"\n"
            + "      }\n"
            + "   ],\n"
            + "   \"base\": \"stations\",\n"
            + "   \"main\": {\n"
            + "      \"temp\": TEMPERATURE,\n"
            + "      \"pressure\": 1018,\n"
            + "      \"humidity\": 100,\n"
            + "      \"temp_min\": 21.11,\n"
            + "      \"temp_max\": 22\n"
            + "   },\n"
            + "   \"visibility\": 2500,\n"
            + "   \"wind\": {\n"
            + "      \"speed\": 3.6,\n"
            + "      \"deg\": 350\n"
            + "   },\n"
            + "   \"clouds\": {\n"
            + "      \"all\": 90\n"
            + "   },\n"
            + "   \"dt\": 1551453653,\n"
            + "   \"sys\": {\n"
            + "      \"type\": 1,\n"
            + "      \"id\": 8341,\n"
            + "      \"message\": 0.0039,\n"
            + "      \"country\": \"BR\",\n"
            + "      \"sunrise\": 1551431562,\n"
            + "      \"sunset\": 1551476637\n"
            + "   },\n"
            + "   \"id\": 3470279,\n"
            + "   \"name\": \"CITY_NAME\",\n"
            + "   \"cod\": 200\n"
            + "}";

}
