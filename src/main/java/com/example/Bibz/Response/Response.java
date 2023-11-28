package com.example.Bibz.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    protected LocalDateTime timesTamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String resason;
    protected String message;
    protected String developerMessage;
    protected Map<?,?> data;
}
