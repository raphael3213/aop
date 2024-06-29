package com.learning.aop;

public class JsonSerializationException extends Exception {

    JsonSerializationException(String message){
        super(message);
    }

    JsonSerializationException(String message, Throwable err){
        super(message, err);
    }
}
