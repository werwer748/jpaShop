package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        /**
         * jpa가 트랜잭션 내에서 변경분에 대해 알아서 업데이트 쿼리문을 작성한다.
         * 이것을 변경감지( dirty checking ) 라고 한다.
         * jpa의 기본 메커니즘이라고 함.
         */
        //TX
        book.setName("asdasd");

        //변경감지 == dirtyChecking
        //TX commit
    }
}
