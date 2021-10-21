//package com.example.schoolmangement.jwt;
//
//import io.jsonwebtoken.security.Keys;
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//
//@Component
//@Data
//@ConfigurationProperties(prefix = "application.jwt")
//public class JwtConfig {
//    private String secretKey;
//    private String tokenPrefix;
//    private Integer tokenExpirationAfterDays;
//
//
//
//    public String getAuthorizationHeader(){
//        return HttpHeaders.AUTHORIZATION;
//    }
//}
