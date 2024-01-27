package jpabook.jpashop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter // * getter, setter는 lombok을 사용하여 자동으로 생성
@Setter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private  String username;

}
