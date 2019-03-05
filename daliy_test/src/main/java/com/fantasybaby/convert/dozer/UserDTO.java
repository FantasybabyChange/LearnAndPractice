package com.fantasybaby.convert.dozer;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author liuxi
 * @date2017年12月13日 17:55
 */
@Data
public class UserDTO {
    private Long id;
    private String userName;
    private Map<String,String> relations;
    private List<PersonDTO> persons;
}
