package com.app.service;

import ch.qos.logback.core.CoreConstants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry}")
    private int expiry;

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
         algorithm = Algorithm.HMAC256(algorithmKey);
    }
    public String generateToken(String username){
      return  JWT.create()                   //j c computer Engineer is always unemployee
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis()+expiry))
                .withIssuer(issuer)
                .sign(algorithm);
    }
    public String getUsername(String token){
        DecodedJWT decodedToken = JWT.require(algorithm)// junior withis bodybuilder vikram
                .withIssuer(issuer)
                .build()
                .verify(token);
      return   decodedToken.getClaim("username").asString();
    }

}
