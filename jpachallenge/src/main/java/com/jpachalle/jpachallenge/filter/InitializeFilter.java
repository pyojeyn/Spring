package com.jpachalle.jpachallenge.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Order(-104)
@Component
public class InitializeFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        String uuid = UUID.randomUUID().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
        String referer = request.getHeader("referer");
        String userAgent = request.getHeader("user-agent");
        String query = request.getQueryString();
        request.setAttribute("requestId", uuid);

        log.info("===============================================");
        log.info("==================== BEGIN ====================");
        log.info("Request URI ===> " + '[' + method + ']' + uri);
        log.info("Request UUID ===> " + uuid);
        log.info("Request IP ===> " + ip);
        if (referer != null) {
            log.info("Request Referer ===> " + referer);
        }
        if (userAgent != null) {
            log.info("Request Agent ===> " + userAgent);
        }
        if (query != null) {
            log.info("Request Query ===> " + query);
        }

        filterChain.doFilter(request, response);

        log.info("===================  END  =====================");
        log.info("===============================================");
    }
}
