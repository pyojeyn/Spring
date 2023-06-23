package com.example.helloaop.service.Impl;

import com.example.helloaop.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceImpl implements TestService {
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
}
