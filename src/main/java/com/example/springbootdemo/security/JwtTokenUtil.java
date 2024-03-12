package com.example.springbootdemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {

    public static final String SECRET_KEY = "7134743777217A25432A462D4A614E645267556B58703272357538782F413F44";
    private final Date expiredDate = new Date(System.currentTimeMillis() + 10 * 60 * 1000);

    public String getToken(String username) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(signKey(), SignatureAlgorithm.HS256)
                .setSubject(username)
                .setIssuer("noreply@gmail.com")
                .compact();
    }

    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUsername(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key signKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
