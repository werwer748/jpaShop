package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

/*
* 값타입은 immutable하게 설계되야 한다.*/

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {}

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
