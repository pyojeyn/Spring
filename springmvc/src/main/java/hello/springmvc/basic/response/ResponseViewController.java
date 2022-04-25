package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 0426 응답 - 정적 리소스, 뷰 템플릿
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseView1(){
        ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello");
        return mav;
    }

//    @ResponseBody // "response/hello" 문자열 그대로 출력
    @RequestMapping("/response-view-v2")
    public String responseView2(Model model){
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    // 권장하지 않음 컨트롤러의 경로와 view 의 논리적인 이름이 똑같으면 작동함.
    @RequestMapping("/response/hello")
    public void responseView3(Model model){
        model.addAttribute("data", "hello!");
    }
}
