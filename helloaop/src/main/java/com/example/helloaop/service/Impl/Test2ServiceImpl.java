package com.example.helloaop.service.Impl;

import com.example.helloaop.service.Test2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Test2ServiceImpl implements Test2Service {

    @Override
    public void only() {
        log.info("Test2ServiceImpl.only() 메서드 호출");
    }
}
