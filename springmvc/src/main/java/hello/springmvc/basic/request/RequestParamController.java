package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody // @RestController랑 같은 역할
    @RequestMapping("request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge){
        log.info("username={}, age={}", memberName, memberAge);
        return "ok"; // @Controller 이 달려있는 상태인데 반환값이 String 이면 그 String 으로 된 view 를 찾게 된다. 그래서 @ResponseBody 를 달아주게 됨 => Http 의 응답에 박히게 됨
    }

    @ResponseBody
    @RequestMapping("request-param-v3")
    public String requestParamV3(@RequestParam String username, // name 속성을 지정 안해주면 실제 파라미터랑 변수명이랑 일치해야 한다.
                                 @RequestParam int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("request-param-v4")
    public String requestParamV4(String username, int age){ // String, int, Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("request-param-required")                                                   // int 는 null이 들어갈 수 없기 때문에 Integer 로 해줘야함
    public String requestParamRequired(@RequestParam(required = true) String username, @RequestParam(required = false) Integer age){ // String, int, Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("request-param-default")                                                   // int 는 null이 들어갈 수 없기 때문에 Integer 로 해줘야함
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                      @RequestParam(required = false, defaultValue = "-1") int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("request-param-map")                                                   // int 는 null이 들어갈 수 없기 때문에 Integer 로 해줘야함
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }
}
