package com.example.helloaop.controller;

import com.example.helloaop.service.Test2Service;
import com.example.helloaop.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;
    private final Test2Service test2Service;

    @GetMapping("helloAop")
    public void helloAop(){
        testService.helloaop();
    }

    @GetMapping("onlyMethodInAllService")
    public void onlyMethodInAllService(){
        testService.only();
    }

    @GetMapping("onlyMethodWithOneParam")
    public void onlyMethodWithOneParamInAllService(@RequestParam int num){
        testService.only(num);
    }

    @GetMapping("onlyMethodWithTwoParamInAllService")
    public void onlyMethodWithTwoParamInAllService(@RequestParam Map<String, String> params){
        // 두개 이상의 쿼리파라미터를 받을 때에는 Map 으로 받자.
        testService.only(Integer.parseInt(params.get("num")), params.get("text"));
    }

    @GetMapping("onlyMethodInTest2Svc")
    public void onlyMethodInTest2Svc(){
        test2Service.only();
    }



    @GetMapping("tokentest")
    public void tokenTest(){
        testService.tokenTest();
    }


}
