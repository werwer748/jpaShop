package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext //스프링이 생성한 jpa 엔티티 매니저를 주입
    private final EntityManager em;

    public void save(Member member) {
        // persist로 저장
        em.persist(member);
    }

    public Member findOne(Long id) {
        // find로 조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // 전체 조회 쿼리를 사용
        // 엔티티 객체를 상대로 쿼리한다고 이해하자
        return em.createQuery("select m from Member  m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
