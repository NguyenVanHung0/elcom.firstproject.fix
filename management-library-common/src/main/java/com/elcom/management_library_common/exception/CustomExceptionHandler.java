
package com.elcom.management_library_common.exception;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {
"com.elcom.management_library_api", 
"com.elcom.management_library_common", 
"com.elcom.management_library_data"})
public class CustomExceptionHandler {
    
    // Xử lý exception NoSuchElementException bắn ra
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchElementException(NoSuchElementException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }
    
    // Xử lý Exception Validate
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(Exception e) {
         return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }
    
    // Xử lý các exception chưa được định nghĩa
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnwantedException(Exception e) {
         return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
