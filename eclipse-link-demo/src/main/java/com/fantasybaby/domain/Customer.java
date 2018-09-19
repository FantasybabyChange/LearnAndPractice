package com.fantasybaby.domain;

import lombok.Data;

import java.util.List;

/**
 * @author reid.liu
 * @date 2018-09-19 19:21
 */
@Data
public class Customer {
    private String firstName;
    private String lastName;
    private Address address;
    private List<PhoneNumber> phoneNumbers;

}
