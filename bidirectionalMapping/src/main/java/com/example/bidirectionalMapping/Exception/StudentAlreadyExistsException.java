package com.example.bidirectionalMapping.Exception;

public class StudentAlreadyExistsException extends RuntimeException{
    public StudentAlreadyExistsException(String message){
        super(message);
    }
}
