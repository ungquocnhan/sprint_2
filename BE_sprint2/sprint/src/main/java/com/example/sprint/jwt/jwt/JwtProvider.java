package com.example.sprint.jwt.jwt;

import com.example.sprint.jwt.user_principal.AccountPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);// ghi log trong class jwtprovider
    private String jwtSecret = "nhan09skbk@gmail.com"; // chữ ký của token
    private int jwtExpiration = 86400; // thời gian sống token 1 ngày

    public String createToken(Authentication authentication) {
        AccountPrincipal accountPrincipal = (AccountPrincipal) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(accountPrincipal.getUsername()) // thêm vào
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) { // valid token
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return true;

        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {Token không hộ trợ}", e);
        } catch (MalformedJwtException e) {
            logger.error("The token invalid format -> Message: {Token không đúng định dạng}", e);

        } catch (SignatureException e) {
            logger.error("Invalid JWT Signature -> Message: {Token không hợp lệ}", e);

        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {Token có khoảng trắng}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {thời gian sống}", e);
        }
        return false;
    }

    public String getUserNameFromToken(String token){
        String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject(); // lấy ra
        return username;
    }
}
