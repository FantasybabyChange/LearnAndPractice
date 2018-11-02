package com.fantasybaby.ireport.walle;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class Order {
	private static final long serialVersionUID = 5027240944435145815L;
	private String orderNumber;
	private Date orderDate;
	private Date shipDate;
	private Date shipDeadline;
	private String shipToName;
	private String shipToPhone;
	private String shipToAddress;
	private String shipToZipcode;
	private String shipFromName;
	private String shipFromPhone;
	private String shipFromAddress;
	private String shipFromZipcode;
	private String logisticsCompany;
	private String expressPaymentWay;
	private String shipToDestination;
	private String shipToStoreName;
	private String remark;
	private String expressNo;
	private Boolean isTaobaoOrder;
	private String externalState;
	private Float price;
	private List<OrderDetail> orderDetails;
	private String str1;
	private String str2;
	private String str3;
	

}
