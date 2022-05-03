package com.company.data.companyData.exception;


import java.util.HashMap;
import java.util.Map;

public class DublicateValueException extends Throwable {

    private String  message ;

    Map<String, String> errors = new HashMap<>();
     @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public DublicateValueException(String message, Map<String, String> map) {
        this.message = message;
        this.errors = map;
    }

    public DublicateValueException()
    {

    }
}
