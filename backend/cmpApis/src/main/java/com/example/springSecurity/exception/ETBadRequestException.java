package com.example.springSecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ETBadRequestException extends RuntimeException{
    public ETBadRequestException(String message){
        super(message);
    }
}
