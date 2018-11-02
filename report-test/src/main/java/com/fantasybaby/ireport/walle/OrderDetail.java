package com.fantasybaby.ireport.walle;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
@Data
public class OrderDetail {
	private String skuNumber;
	private String externalCode;
	private String expiryDate;
	private String qty;
	private String uom;
	
}
