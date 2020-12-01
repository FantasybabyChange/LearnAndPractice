package com.fantasybaby.study.java8test.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author liuxi
 * @date2017年12月13日 17:55
 */
@Data
@ToString
@NoArgsConstructor
public class UserDO extends PersonDO{
    //    @Mapping("id")
    private String userID;
    private String userName;
    private Map<String,String> relations;
    private List<PersonDO> persons;

}
