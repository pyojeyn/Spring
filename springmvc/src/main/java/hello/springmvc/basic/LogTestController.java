package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        // 이렇게 사용하면 안됨
        log.info("info log="+name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name); // 중요한 정보
        log.warn("warn log={}", name); // 경고
        log.error("error log={}", name); // 에러

        return "ok";
    }
}
