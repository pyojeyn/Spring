package com.jpachalle.jpachallenge.service.impl;

import com.jpachalle.jpachallenge.service.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Service
public class CodeServiceImpl implements CodeService {
    @Override
    public String createCode(List<String> oldCode) {
        String code = null;

        while(true){
            code = RandomStringUtils.random(12, true, true);

            if(!oldCode.contains(code)){
                break;
            }
            return code;
        }

        return code;
    }
}
