package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 파라미터로 넘어온 값 변수에 담아주고
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // Member 객체를 생성해서
        Member member = new Member(username, age);
        // MemberRepository 저장소에 저장해주고
        memberRepository.save(member);

        // 모델뷰에 논리적인 url이름을 생성자에 같이 넣어줘서 객체를 만들고
        ModelView mv = new ModelView("save-result");
        // 그 객체의 모델Map에다가 member를 같이 넣어준다.
        mv.getModel().put("member", member);
        return mv;
    }
}
