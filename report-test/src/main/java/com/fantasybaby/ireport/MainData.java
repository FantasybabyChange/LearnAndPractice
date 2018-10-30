package com.fantasybaby.ireport;

import com.fantasybaby.file.freemarker.UserBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author reid.liu
 * @date 2018-10-30 17:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainData implements Serializable {
    private String barCode;
    private String code1;
    private String code2;
    private String code3;
    private String state;
    private List<UserBean> items;
}
