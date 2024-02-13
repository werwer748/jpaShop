package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 변경감지기능 사용법
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) { // 파라미터가 많으면 Dto를 만들어서 활용해도 좋음

        // 이렇게 가져오면 영속상태가 되기 때문에 merge를 할 필요가 없음 and 병합(merge는 위험하다.) => 귀찮아도 변경감지를 사용하도록 하자.
        Book findItem = (Book) itemRepository.findOne(itemId);

        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
//        findItem.setAuthor(param.getAuthor());
//        findItem.setIsbn(param.getIsbn());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
