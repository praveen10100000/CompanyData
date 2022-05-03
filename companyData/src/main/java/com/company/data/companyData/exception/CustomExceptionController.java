package com.company.data.companyData.exception;

import com.company.data.companyData.entity.ResponseInJson;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionController extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
              String fieldName = ((FieldError) error).getField();
              String message = error.getDefaultMessage();

              errors.put(fieldName , message);
        });
        ResponseInJson responseInJson = new ResponseInJson();
        responseInJson.setCode(400);
        responseInJson.setStatus("Bad Request");
        responseInJson.setMessage("Please Enter Valid Details");
        responseInJson.setErrors(errors);
        return new ResponseEntity<Object>(responseInJson , HttpStatus.BAD_REQUEST);

    }

     @ExceptionHandler(DublicateValueException.class)
     public ResponseEntity<?> handleDublicateException(DublicateValueException e)
     {
         ResponseInJson responseInJson = new ResponseInJson();
         if(!e.getErrors().isEmpty()){
             responseInJson.setErrors(e.getErrors());
         }
         responseInJson.setCode(400);
         responseInJson.setStatus("Bad Request");
         responseInJson.setMessage(e.getMessage());
         return new ResponseEntity<>(responseInJson , HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler({EntityNotFoundException.class})
     public ResponseEntity<?> handleValidIdException(EntityNotFoundException e)
     {
         ResponseInJson responseInJson  =new ResponseInJson() ;
         responseInJson.setCode(400);
         responseInJson.setStatus("Bad Request");
         responseInJson.setMessage("ID doesn't Exists");
         return new ResponseEntity<>(responseInJson , HttpStatus.BAD_REQUEST);
     }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<?> handleValidIdException(EmptyResultDataAccessException e)
    {
        ResponseInJson responseInJson  =new ResponseInJson() ;
        responseInJson.setCode(400);
        responseInJson.setStatus("Bad Request");
        responseInJson.setMessage("ID doesn't Exists");
        return new ResponseEntity<>(responseInJson , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleConstraintVoilationException(MethodArgumentTypeMismatchException e)
    {
        ResponseInJson responseInJson  =new ResponseInJson() ;
        responseInJson.setCode(400);
        responseInJson.setStatus("Bad Request");
        responseInJson.setMessage("Id format is mismatch");
        return new ResponseEntity<>(responseInJson , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintVoilationException(ConstraintViolationException e)
    {
        ResponseInJson responseInJson  =new ResponseInJson() ;
        responseInJson.setCode(400);
        responseInJson.setStatus("Bad Request");
        responseInJson.setMessage("Please Enter right name ");
        return new ResponseEntity<>(responseInJson , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<?> handleIntegrityVoilationException(DataIntegrityViolationException e)
    {
        ResponseInJson responseInJson  =new ResponseInJson() ;
        responseInJson.setCode(400);
        responseInJson.setStatus("Bad Request");
        responseInJson.setMessage("Please Write Valid Details");

        return new ResponseEntity<>(responseInJson , HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleTypeMismatch(ex, headers, status, request);
    }



}