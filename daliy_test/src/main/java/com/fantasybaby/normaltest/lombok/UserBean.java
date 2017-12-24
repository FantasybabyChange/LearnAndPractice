package com.fantasybaby.normaltest.lombok;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by fantasybaby on 2017-06-05.
 */
//@Setter@Getter
@Data
@ToString
public class UserBean implements Serializable{
    private static final long serialVersionUID = -9212558713833627910L;
    private Integer id;
    private String userName;
    private  Integer age;
}
