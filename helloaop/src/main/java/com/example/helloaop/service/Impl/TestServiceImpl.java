package com.example.helloaop.service.Impl;

import com.example.helloaop.service.TestService;
import com.example.helloaop.utils.AuthUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    
    private final AuthUtils authUtils;

    @Override
    public void helloaop() {
       log.info("TestServiceImpl.helloaop 호출");

    }

    @Override
    public void only() {
        log.info("TestServiceImpl.only method 호출");
    }

    @Override
    public void only(int num) {
        log.info("num={}", num);
        log.info("TestServiceImpl.only(int num) 호출");
    }

    @Override
    public void only(int num, String text) {
        log.info("num={}", num);
        log.info("text={}", text);
        log.info("TestServiceImpl.only(int num, String text) 호출");
    }

    @Override
    public void tokenTest() {
        List<String> roles = List.of("USER");

        String accessToken = authUtils.generateToken("1", "Access", roles);
        String refreshToken = authUtils.generateToken("1", "Refresh", roles);

        log.info("accessToken={}", accessToken);
        log.info("refreshToken={}", refreshToken);
        Date refreshExpiration = authUtils.getRefreshTokenExpirationDate(refreshToken);
        log.info("refreshExpiration={}", refreshExpiration); // Wed Aug 02 20:51:50 KST 2023

        boolean refreshValid = authUtils.validateRefreshToken(refreshToken);
        log.info("refreshValid={}", refreshValid);

        Claims claims = authUtils.parseClaims(accessToken);
        Date experation = claims.getExpiration();
        log.info("accessExpiration={}", experation); // Tue Jul 04 20:54:40 KST 2023 -> int
                                                    // Tue Jul 04 20:56:23 KST 2023 -> long


    }
}
