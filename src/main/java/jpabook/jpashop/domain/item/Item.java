package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
* @Inheritance(strategy = InheritanceType.?)
* 상속관계 클래스 사용시 전략을 잡아줘야 한다.
* SINGLE_TABLE: 한테이블에 다 때려박는거
*
* JOINED: 가장 정규화된 스타일?
*
* TABLE_PER_CLASS: 지금 프로젝트를 예로들면 Book, Album, Movie 세 개의 테이블이 나오는 형태
*/

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // 자식테이블 선택에 관여함
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직? 엔티티안에서 처리할 수 있는 로직은 엔티티안에서 처리하는게 좋다(응집도면에서)==//

    /**
     * stock 증가
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;

        if (restStock < 0) throw new NotEnoughStockException("need more stock");

        this.stockQuantity = restStock;
    }
}
