package com.example.apigateway.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex) {
        ProblemDetail errorDetail = null;
        if (ex instanceof RuntimeException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Authentication Failure");
        }

        if (ex instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Signature not valid");
        }
        if (ex instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Token already expired !");
        }
        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
        }
        if (ex instanceof Exception) {
            System.out.println("Exception");
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Internal Server Error");
        }
        return errorDetail;
    }

}