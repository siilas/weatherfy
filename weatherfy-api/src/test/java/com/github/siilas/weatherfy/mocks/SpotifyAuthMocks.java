package com.github.siilas.weatherfy.mocks;

public final class SpotifyAuthMocks {

    private SpotifyAuthMocks() {
        throw new IllegalStateException();
    }
    
    public static final String SUCCESS_MOCK = "{\n"
            + "   \"access_token\": \"NgCXRKcMzYjw\",\n"
            + "   \"token_type\": \"bearer\",\n"
            + "   \"expires_in\": 3600\n"
            + "}";

}
