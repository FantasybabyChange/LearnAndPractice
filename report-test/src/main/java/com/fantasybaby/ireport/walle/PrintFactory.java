package com.fantasybaby.ireport.walle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrintFactory {

	public static Collection<Order> print() {
		Collection<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setOrderNumber("TEST-00013-1");
		order.setShipToAddress("hahahahahaha");
		order.setShipToName("hahahahahaha");
		order.setShipToPhone("hahahahahaha");
		order.setShipFromName("hahahahahaha");
		order.setShipFromPhone("hahahahahaha");
		order.setShipFromAddress("hahahahahaha");
		order.setRemark("中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文");
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (int i = 0; i < 40; i++) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setExpiryDate("2018-10-10 12:30:20");
			orderDetail.setSkuNumber("123");
			orderDetail.setExternalCode("aaaaaaaaaaaaaaaaaaaaaaaaaaaa"+i);
			orderDetail.setUom("UTF"+i);
			orderDetail.setQty("123");
			orderDetails.add(orderDetail);
		}


		order.setOrderDetails(orderDetails);

		order.setStr1("TEST");
		order.setStr2("0013-1");
		order.setStr3("A-CONSOL");
		orders.add(order);
		return orders;
	}
}
