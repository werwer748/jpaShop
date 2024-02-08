package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

// jpa가 db랑 엮여서 스프링위에서 돌아가는걸 테스트하기 위해 추가 - 순수한 단위테스트 X
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    // 테스트케이스라 다른것들이 참조할게 없으니 필드인젝션으로 작성해도 무관
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kang");

        // when
        Long saveId = memberService.join(member);

        // then
//        em.flush(); // 영속성 컨텍스트에 올라온 변경사항 쿼리를 날린다. 실제 등록 쿼리를 볼 수 있음
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kang");

        Member member2 = new Member();
        member2.setName("kang");

        // when
        memberService.join(member1);
        memberService.join(member2); // 예외 발생해야 함!

        // then
        fail("예외가 발생해야 한다.");
    }
}