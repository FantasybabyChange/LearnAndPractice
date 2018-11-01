package com.fantasybaby.ireport;

import com.fantasybaby.file.freemarker.UserBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author reid.liu
 * @date 2018-10-30 17:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxData implements Serializable {
   private String boxID;
   private Integer customerID;
   private Integer state;
   private Collection<BoxDetails> details;
}
