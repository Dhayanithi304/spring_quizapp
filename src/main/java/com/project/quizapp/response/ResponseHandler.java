package com.project.quizapp.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generate(String message, HttpStatus status, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("message", message);
        map.put("data", data);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generate(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("message", message);
        return new ResponseEntity<>(map, status);
    }
}
