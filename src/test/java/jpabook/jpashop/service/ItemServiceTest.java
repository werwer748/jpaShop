package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 물건등록() throws Exception {
        // given
        Book book = new Book();
        book.setAuthor("hugo");

        // when
        itemService.saveItem(book);

        // then
        assertEquals(book, itemService.findOne(book.getId()));
    }

    // em.merge(item) 는 잘 모르겠으니 중복등록의 경우는 일단 넘어가자.

    @Test
    public void 물건리스트_확인() throws Exception {
        // given
        Book book1 = new Book();
        book1.setAuthor("hugo");

        Book book2 = new Book();
        book2.setAuthor("zugo");

        // when
        itemService.saveItem(book1);
        itemService.saveItem(book2);

        // then
        List<Item> newItems = Arrays.asList(book1, book2);
        List<Item> getItems = itemService.findItems();
        assertEquals(newItems.size(), getItems.size());
        assertTrue(newItems.containsAll(getItems) && getItems.containsAll(newItems));
    }
}