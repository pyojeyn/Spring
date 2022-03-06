package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; //Cmd + Shift + Enter 단축키 : ; 까지 자동완성 해준다.

    /*의존관계 자동 주입 ComponentScan 을 쓰게 되면 Autowired 를 쓰게 된다. @Bean으로 등록을 하지 않기 때문
    *  이전에 AppConfig 에서는 @Bean으로 직접 설정 정보를 작성했고, 의존관계도 직접 명시했다. 이제는 이런 설정 정보 자체가 없기 때문에 , 의존관계 주입도 이 클래스안에서 해결해야한다.
    * */
    @Autowired  // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

/*
*   설계 변경으로 MemberServiceImpl 은 MemoryMemberRepository 를 의존하지 않는다.
*   단지 MemoryRepository 인터페이스에만 의존한다.
*   MemberServiceImpl 의 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
*   MemberServiceImpl 의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다.
*   MemberServiceImpl 은 이제부터 "의존관계에 대한 고민"은 외부에 맡기고 "실행에만 집중"하면 된다. join, findMember
* */