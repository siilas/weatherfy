package com.github.siilas.weatherfy.core.http;

import org.springframework.http.ResponseEntity;

public final class HttpStatusUtils {

	private HttpStatusUtils() {
		throw new IllegalStateException();
	}
	
	public static boolean isNotFound(ResponseEntity<?> response) {
		return response.getStatusCodeValue() == 404;
	}
	
}
