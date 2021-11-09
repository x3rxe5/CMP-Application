package com.example.CMPApplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ETBadRequest extends RuntimeException{
    public ETBadRequest(String message){
        super();
    }
}
