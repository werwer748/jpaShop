package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // 기본으로 ORDINAL 로 잡히는데 절대로 사용하면 안됨(enum 순서대로 숫자가 박히는데 순서가 틀어지면 일이 커짐)
    private DeliveryStatus status; // READY, COMP
}
