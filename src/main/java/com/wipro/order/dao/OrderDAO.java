package com.wipro.order.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wipro.order.bean.OrderBean;
import com.wipro.order.util.DBUtil;

public class OrderDAO {
	
	private Connection con = DBUtil.getConnection();
	
	private int seq = 0;
	
	public String createRecord(OrderBean bean) {
		try {
		PreparedStatement ps = con.prepareStatement("INSERT INTO Order_TB Value(?,?,?,?,?,?,?)");
		
		ps.setString(1, bean.getOrderId());
		ps.setString(2, bean.getCustomerName());
		ps.setString(3, bean.getOrderItem());
		ps.setDate(4, bean.getOrderDate());
		ps.setDate(5, bean.getDeliveryDate());
		ps.setString(6, bean.getStatus());
		ps.setString(7, bean.getRemarks());
		
		if(ps.executeUpdate() > 0) {
			return bean.getOrderId();
		}
		
		return "FAIL";
		}catch(Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	public OrderBean fetchRecord(String customerName, Date orderDate) {
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * from Order_TB where CustomerName = ? AND Order_Date = ?");
			
			ps.setString(1, customerName);
			ps.setDate(2, orderDate);
			
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			
			OrderBean order = new OrderBean();
			
			order.setOrderId(rs.getString("OrderId"));
			order.setCustomerName(rs.getString("CustormerName"));
			order.setOrderItem(rs.getString("OrderItem"));
			order.setOrderDate(rs.getDate("Order_Date"));
			order.setDeliveryDate(rs.getDate("Delivery_Date"));
			order.setStatus(rs.getString("Status"));
			order.setRemarks(rs.getString("Remarks"));
			
			return order;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public String generateOrderID(String customerName, Date orderDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String temp = f.format(orderDate);
		
		return temp + customerName.substring(0, 2) + generateSequence();
	}
	
	private int generateSequence() {
		seq+=1;
		return seq;
	}
	
	public boolean recordExists(String customerName, Date orderDate) {
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * from Order_TB where CustomerName = ? AND Order_Date = ?");
			
			ps.setString(1, customerName);
			ps.setDate(2, orderDate);
			
			if(ps.execute()) {
				return true;
			}
			
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public List<OrderBean> fetchAllRecords(){
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT * FROM Order_TB");
			
			ArrayList <OrderBean> orders = new ArrayList<>();
			
			while(rs.next()) {
				OrderBean order = new OrderBean();
				
				order.setOrderId(rs.getString("OrderId"));
				order.setCustomerName(rs.getString("CustormerName"));
				order.setOrderItem(rs.getString("OrderItem"));
				order.setOrderDate(rs.getDate("Order_Date"));
				order.setDeliveryDate(rs.getDate("Delivery_Date"));
				order.setStatus(rs.getString("Status"));
				order.setRemarks(rs.getString("Remarks"));
				
				orders.add(order);
			}
			
			return orders;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ArrayList<OrderBean> ();
		}
	}

	
}
