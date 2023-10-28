package com.example.apigateway.filter;

import com.example.apigateway.config.AppConfig;
import com.example.apigateway.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private RestTemplate template;

    @Autowired
    JwtUtils jwtUtils;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
//              header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
                    throw new RuntimeException("Authorization header is missing");
                }

                String authorizationHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    String token = authorizationHeader.substring(7);
                    // validate token
                    authorizationHeader = authorizationHeader.substring(7);
                    try {
//                        template.getForObject("http://localhost:8093/auth/validate?token=" + authorizationHeader, String.class);
//                        System.out.println("validated....");

                        jwtUtils.validateJwtToken(authorizationHeader);
                        System.out.println("validated....");
                    } catch (RuntimeException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                } else {
                    throw new RuntimeException("Authorization header is missing");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
