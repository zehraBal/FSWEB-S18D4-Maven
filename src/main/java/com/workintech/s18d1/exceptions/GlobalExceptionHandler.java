package com.workintech.s18d1.exceptions;

import com.workintech.s18d1.entity.Burger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleException(BurgerException exception){
        BurgerErrorResponse response=new BurgerErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response,exception.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleException(Exception exception){
        BurgerErrorResponse response=new BurgerErrorResponse( exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
