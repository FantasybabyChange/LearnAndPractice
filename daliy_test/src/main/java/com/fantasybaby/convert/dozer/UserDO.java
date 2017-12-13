package com.fantasybaby.convert.dozer;

import java.util.function.Consumer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.dozer.Mapping;

/**
 * @author liuxi
 * @date2017年12月13日 17:55
 */
@Data
@ToString
@NoArgsConstructor
public class UserDO {
    @Mapping("id")
    private String userID;
    private String userName;

}
