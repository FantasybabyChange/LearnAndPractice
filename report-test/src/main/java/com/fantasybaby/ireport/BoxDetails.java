package com.fantasybaby.ireport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author reid.liu
 * @date 2018-10-30 17:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxDetails implements Serializable {
   private String boxDetailID;
   private String skuID;
   private String batchNumber;
   private Boolean isBox;
}
