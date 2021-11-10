package com.example.CMPApplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ETAuthExceptions extends RuntimeException{

    public ETAuthExceptions(String message){
        super(message);
    }
}
