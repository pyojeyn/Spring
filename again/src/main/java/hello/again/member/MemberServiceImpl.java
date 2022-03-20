package hello.again.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); // CMD + SHIFT + ENTER => ;까지 자동완성

    private final MemberRepository memberRepository; // 인터페이스(추상화) 에만 의존!!! 구현체는 밖에서알아서 해줄것임(AppConfig)

    // 생성자 주입
    @Autowired // 자동 의존관계 주입
    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자를 통해서 주입 new 는 AppConfig 에서 ~
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

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

// DI Dependency Injection