package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
/**
 * jpa의 데이터 변경이나 모든 로직들은 가급적이면 트랜젝션안에서 일어나는게 좋다.
 * spring에서 제공해주는 어노테이션을 사용하자(쓸 수 있는 옵션들이 많다.)
 * readOnly = true는 조회시 성능을 최적화해줌. 근데 데이터 변경이안되서 읽기 외에는 사용하면 안됨
 */
@Transactional(readOnly = true) // 여기서 사용하면 이 클래스안의 모든 메서드에 트랜잭션이 적용된다.
// @AllArgsConstructor // 자동으로 생성자를 만들어준다.
@RequiredArgsConstructor // final이 붙은 필드들만으로 생성자를 만들어준다.
public class MemberService {

    /**
     * 필드인젝션... 권장되지는 않음
     * 테스트하거나 할 때 다른걸 주입하고 해야하는데 그렇게 사용하기가 힘듬
     * @Autowired
     * private MemberRepository memberRepository;
     */

    /**
     * 방법 1. 세터 인젝션
     * 테스트 작성시 유연하게 사용 가능하다.
     * 런타임시 누군가 바꿔버릴 수 있다. 동작 중 바뀌는건 좋지 않음...
     * private MemberRepository memberRepository;
     *
     * @Autowired
     * public void setMemberRepository(MemberRepository memberRepository) {
     *     this.memberRepository = memberRepository;
     * }
     */

    /**
     * 방법 2. 생성자 인젝션
     * 생성시점에 딱 정해지고 무언가 잘못 작성시 바로 에러가 떠서 확인이 쉽다.
     * 변경할 일이 없기 때문에 final로 선언 가능
     * 컴파일 시점 체크에 유용하다.
     */
    private final MemberRepository memberRepository;

//    @Autowired // 생성자가 하나밖에 없을경우 생략가능
    /*
    * @RequiredArgsConstructor가 만들어줘서 작성하지 않아도 됨...Lombok
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /**
     * 회원 가입
     */
    @Transactional // 여기만 따로 어노테이션을 달아서 기본설정인 readOnly = false가 되게끔..
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 이름으로 회원 중복 검사
    private void validateDuplicateMember(Member member) {
        /**
         * 현재 방식은 동시에 호출하거나 멀티스레드를 사용하는 등의 상황에서 이름이 중복되는 회원이 생길 수 있음
         * 그래서 테이블 생성시 애초에 name에 unique를 걸거나 하는게 좋음(걸어보자 - 성공)
         */
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
//    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     */
//    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
