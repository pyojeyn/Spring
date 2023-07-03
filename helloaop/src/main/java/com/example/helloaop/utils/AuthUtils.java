package com.example.helloaop.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class AuthUtils {
    private static final String AUTHORITIES_KEY = "AuthoritiesKey";

    @Value("${spring.jwt.accessSecret}")
    private String ACCESS_TOKEN_SECRET_KEY;

    @Value("${spring.jwt.refreshSecret}")
    private String REFRESH_TOKEN_SECRET_KEY;

    final static long AccessTokenExpiresIn = 60L * 60 * 24 * 1000; // 1 days
    final static long RefreshTokenExpiresIn = 60L * 60 * 24 * 1000 * 30; // 30 days

    public String generateToken(String userId, String tokenType, List<String> roles) {

        long now = (new Date()).getTime();

        if(tokenType.equals("Access")) {

            log.info("0703_ACCESS_TOKEN_SECRET_KEY={}",ACCESS_TOKEN_SECRET_KEY);


            byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET_KEY);
            Key key = Keys.hmacShaKeyFor(keyBytes);

            Date accessTokenExpiresIn = new Date(now + AccessTokenExpiresIn);

            String testaccessToken = Jwts.builder()
                    .setSubject(userId)       // payload "sub": "name"
                    .claim(AUTHORITIES_KEY, roles)        // payload "auth": "ROLE_USER"
                    .setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022 (예시)
                    .signWith(key, SignatureAlgorithm.HS512)    // header "alg": "HS512"
                    .compact();

            log.info("testaccessToken={}", testaccessToken);

            return Jwts.builder()
                    .setSubject(userId)       // payload "sub": "name"
                    .claim(AUTHORITIES_KEY, roles)        // payload "auth": "ROLE_USER"
                    .setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022 (예시)
                    .signWith(key, SignatureAlgorithm.HS512)    // header "alg": "HS512"
                    .compact();

        } else {
            log.info("0703_REFRESH_TOKEN_SECRET_KEY={}",REFRESH_TOKEN_SECRET_KEY);

            byte[] keyBytes = Decoders.BASE64.decode(REFRESH_TOKEN_SECRET_KEY);
            Key key = Keys.hmacShaKeyFor(keyBytes);


            Date refreshTokenExpiresIn = new Date(now + RefreshTokenExpiresIn);
            log.info("refreshTokenExpiresIn={}", refreshTokenExpiresIn); // [TODO] 0703 현재 이거 6월14일로 나옴. 이거 정확히 한달뒤로 나오도록 수정.

            return Jwts.builder()
                    .setExpiration(refreshTokenExpiresIn)
                    .signWith(key, SignatureAlgorithm.HS512)
                    .compact();
        }
    }
    public Date getRefreshTokenExpirationDate(String token){
        byte[] keyBytes = Decoders.BASE64.decode(REFRESH_TOKEN_SECRET_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

        Date expiation = jws.getBody().getExpiration();

        return expiation;
    }

    public boolean validateRefreshToken(String token) {
        try {
            // 검증
            byte[] keyBytes = Decoders.BASE64.decode(REFRESH_TOKEN_SECRET_KEY);
            Key key = Keys.hmacShaKeyFor(keyBytes);
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

            //refresh 토큰의 만료시간이 지나지 않았을 경우, 새로운 access 토큰을 생성합니다.
            if (!claims.getBody().getExpiration().before(new Date())) {
                //return generateToken(claims.getBody().get("sub").toString(), TokenType.Access);
                return true;
            }
        } catch (Exception e) {
            //refresh 토큰이 만료되었을 경우, 로그인이 필요합니다.
        }
        return false;
    }



    public Claims parseClaims(String accessToken) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET_KEY);
            Key key = Keys.hmacShaKeyFor(keyBytes);
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String parseAccessToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(ACCESS_TOKEN_SECRET_KEY)
                    .parseClaimsJws(token);
            return claims.getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateAccessToken(String jwtToken) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET_KEY);
            Key key = Keys.hmacShaKeyFor(keyBytes);
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
