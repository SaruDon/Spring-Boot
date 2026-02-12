package com.example.SpringSecurity63.service;

import com.example.SpringSecurity63.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {


    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }


    public String generateAccessToken(User user){
        return Jwts
                .builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .signWith(getSecretKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() *1000L*30))
                .compact();
    }

    public String generateRefreshToken(User user){
        return Jwts
                .builder()
                .subject(user.getId().toString())
                .signWith(getSecretKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() *1000L*60*60*24*20*6))
                .compact();
    }

    public Long getUserIdFromToken(String token){
        Claims claims =  Jwts
                        .parser()
                        .verifyWith(getSecretKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

        return Long.valueOf(claims.getSubject());
    }

}

