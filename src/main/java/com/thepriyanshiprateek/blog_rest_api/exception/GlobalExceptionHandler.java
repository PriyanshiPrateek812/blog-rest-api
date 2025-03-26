package com.thepriyanshiprateek.blog_rest_api.exception;

import com.thepriyanshiprateek.blog_rest_api.payload.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                       WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetail> handleBlogApiException(BlogApiException ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetail, ex.getStatus());
    }
}
