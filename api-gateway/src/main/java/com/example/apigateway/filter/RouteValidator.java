package com.example.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
     public static final List<String> openApiEndpoints = List.of(
            "/auth-service/auth/login",
            "/auth-service/auth/register",
             "/auth-service/auth/get"
     );

     public Predicate<ServerHttpRequest> isSecured =
             request -> openApiEndpoints
             .stream()
             .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
