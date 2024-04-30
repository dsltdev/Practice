package com.practice.pizzeria.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static String SECRET_KEY = "secret";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
// palabra clave para encimptar
    public String create (String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("pizzeria")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }

//validacion del token
    public Boolean isValid(String jwt) throws JWTVerificationException{
        try{
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
        }catch (JWTVerificationException e){
            return false;
        }
        return true;
    }

    public  String getUsername(String jwt) throws JWTVerificationException{
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
