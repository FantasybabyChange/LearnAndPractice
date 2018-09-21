package com.fantasybaby.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author reid.liu
 * @date 2018-09-19 19:22
 */
@Data
//@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneNumber {
    private String type;
    private String number;
}
