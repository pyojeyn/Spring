//package com.jpachalle.jpachallenge.filter;
//
//import com.jpachalle.jpachallenge.filter.provider.JwtTokenProvider;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final JwtTokenProvider jwtTokenProvider;
//
//    // Jwt Provier 주입
//    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    // Request로 들어오는 Jwt Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를 filterChain에 등록합니다.
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String jwtToken = null;
//        try {
//            jwtToken = jwtTokenProvider.resolveToken(request);
//        } catch (IllegalArgumentException ex) {
//            log.error("Unable to get JWT token", ex);
//            throw ex;
//        }
//
//
//
//        // } catch (ExpiredJwtException ex) {
//        // log.error("JWT Token has expired", ex);
//        // throw new ExpiredJwtException("JWT Token has expired");
//        // } catch (UsernameFromTokenException ex) {
//        // log.error("token valid error:" + ex.getMessage() ,ex);
//        // throw new UsernameFromTokenException("Username from token error");
//        // }
//
//        // 토큰을 가져오면 검증을 한다, null인경우는 안들어온 것
//        log.info("TokenValidate: " + jwtTokenProvider.validateToken(jwtToken));
//
//        if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)) {
//            log.info("AccessToken >>>> " + jwtToken);
//            Authentication auth = jwtTokenProvider.getAuthentication(jwtToken);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
