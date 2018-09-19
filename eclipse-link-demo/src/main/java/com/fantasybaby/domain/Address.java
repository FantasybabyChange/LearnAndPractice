package com.fantasybaby.domain;

/**
 * @author reid.liu
 * @date 2018-09-19 19:21
 */


import lombok.Data;

@Data
public class Address {
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
