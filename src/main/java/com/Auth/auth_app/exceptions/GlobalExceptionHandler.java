package com.Auth.auth_app.exceptions;


import com.Auth.auth_app.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//With help of this we can handle the exception in the project globally, every exception
public class GlobalExceptionHandler {
    //resource not found exception handler :: method
 @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
       ErrorResponse internalServerError = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalServerError);
   }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> IllegalArgumentException(ResourceNotFoundException exception){
        ErrorResponse internalServerError = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(internalServerError);
    }
}
