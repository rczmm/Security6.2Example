package com.example.demologin.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private String serectKey = "feqguifebahjfhabdsbh";

    public String createToken(Map<String, Object> map) {
        return Jwts.builder()
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, serectKey)
                .compact();
    }

    public Claims paresToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(serectKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }
}
