package com.fantasybaby.domain;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author reid.liu
 * @date 2018-09-19 19:21
 */
@Data
//@XmlRootElement(name="customer")
@ToString
public class Customer {
    private String firstName;
    private String lastName;
    private Address address;
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

}
