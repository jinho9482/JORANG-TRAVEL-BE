package com.example.travel_diary.global.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Long expiration;

    public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }


    // Token 생성
    public String createToken(String loginId) {
        String token = Jwts.builder()
                .subject(loginId)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
        return token;
    }

    // Token에서 login id 추출
    public String getLoginIdFromToken(String token) {
        try {
            Claims payload = (Claims) Jwts.parser().verifyWith(secretKey).build().parse(token).getPayload();
            System.out.println(payload.getSubject());
            return payload.getSubject();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse token");
        }

    }


    // Header에서 Token 값 추출
    public String resolveToken(HttpServletRequest request) {
        try {
            System.out.println("************ resolveToken 들어옴 *****************");
            System.out.println(request.getHeader("Authorization"));
            return request.getHeader("Authorization");
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot get token from header");
        }
    }

    // token 이 만료 되지 않았는지?
    public boolean validateToken(String token) {
        try {
            Claims payload = (Claims) Jwts.parser().verifyWith(secretKey).build().parse(token).getPayload();
            return payload.getExpiration().after(new Date());
        } catch (Exception e) {
            throw new IllegalArgumentException("Token is expired");
        }

    }
}
