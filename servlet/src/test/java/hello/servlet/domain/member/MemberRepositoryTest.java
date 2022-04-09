package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest  {

    MemberRepository memberRepository = MemberRepository.getInstance();

    // 테스트 끝나면 무조건 실행 멤버 저장소 초기화.
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){ // ctrl + shift + R 실행
        //given
        Member member = new Member("jane", 27);


        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(member.getId());

        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
