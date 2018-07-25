package com.fantasybaby.domain;

import java.io.Serializable;

/**
 * @author: liuxi
 * @time: 2018/7/25 11:00
 */
public class WordDO implements Serializable {

    private static final long serialVersionUID = -6800937544375851325L;
    private String place;
    private String code;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
