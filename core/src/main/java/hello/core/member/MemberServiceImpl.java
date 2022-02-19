package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; //Cmd + Shift + Enter 단축키 : ; 까지 자동완성 해준다.

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
}

/*
*   설계 변경으로 MemberServiceImpl 은 MemoryMemberRepository 를 의존하지 않는다.
*   단지 MemoryRepository 인터페이스에만 의존한다.
*   MemberServiceImpl 의 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
*   MemberServiceImpl 의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다.
*   MemberServiceImpl 은 이제부터 "의존관계에 대한 고민"은 외부에 맡기고 "실행에만 집중"하면 된다. join, findMember
* */