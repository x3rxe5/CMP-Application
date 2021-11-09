package com.example.CMPApplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ETAuthExceptions extends RuntimeException{
    private String message;

    public ETAuthExceptions(String message){
        super();
        this.message = message;
    }
}
