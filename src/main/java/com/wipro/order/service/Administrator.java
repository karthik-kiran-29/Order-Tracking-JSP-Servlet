package com.wipro.order.service;

import java.sql.Date;
import java.util.List;

import com.wipro.order.bean.OrderBean;
import com.wipro.order.dao.OrderDAO;
import com.wipro.order.util.InvalidInputException;

public class Administrator {
	private OrderDAO order = new OrderDAO();

	public String addRecord(OrderBean bean) {
		try {
			if(bean == null || bean.getCustomerName().isEmpty() || bean.getOrderDate() == null) {
				throw new InvalidInputException();
			}

			if(bean.getCustomerName().length() > 2) {
				return "INVALID CURTOMER NAME";
			}

			if(bean.getOrderItem().length() > 2) {
				return "INVALID ORDER ITEM";
			}

			if(order.recordExists(bean.getCustomerName(), bean.getOrderDate())) {
				return "ALREADY EXISTS";
			}

			bean.setOrderId(order.generateOrderID(bean.getCustomerName(), bean.getOrderDate()));

			return order.createRecord(bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "FAIL";
		}
	}

	public OrderBean viewRecord(String customerName, Date orderDate) {
		return order.fetchRecord(customerName, orderDate);
	}

	public List<OrderBean> viewAllRecords(){
		return order.fetchAllRecords();
	}
}
