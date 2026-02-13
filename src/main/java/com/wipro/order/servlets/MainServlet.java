package com.wipro.order.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.wipro.order.bean.OrderBean;
import com.wipro.order.service.Administrator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	Administrator admin = new Administrator();
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String addRecord(HttpServletRequest request) {
    	OrderBean order = new OrderBean();

    	order.setCustomerName(request.getParameter("customerName"));
    	order.setDeliveryDate(Date.valueOf(request.getParameter("deliveryDate")));
    	order.setOrderDate(Date.valueOf(request.getParameter("orderDate")));
    	order.setOrderItem(request.getParameter("orderItem"));
    	order.setRemarks(request.getParameter("remarks"));
    	order.setStatus(request.getParameter("status"));

    	String result = admin.addRecord(order);

    	System.out.println("Add Record in Servlet: " + result);

    	return result;
    }

    public OrderBean viewRecord(HttpServletRequest request) {
    	return admin.viewRecord(request.getParameter("customerName"),Date.valueOf(request.getParameter("orderDate")));
    }

    public List<OrderBean> viewAllRecords(HttpServletRequest request){
    	return admin.viewAllRecords();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Operation = request.getParameter("operation");

		switch(Operation) {
		case "newRecord":
			String result = addRecord(request);
			if(result == null || result.equalsIgnoreCase("FAIL")) {
				response.sendRedirect("error.html");
			}else {
				response.sendRedirect("success.html");
			}
			break;
		case "viewRecord":
			OrderBean order = viewRecord(request);

			if(order==null) {
				request.setAttribute("message", "No matching records exists! Please try again!");
			}else {
				request.setAttribute("order", order);
			}
			RequestDispatcher rd = request.getRequestDispatcher("displayOrder.jsp");
			rd.forward(request,response);
			break;
		case "viewAllRecords":
			List<OrderBean> list = viewAllRecords(request);

			if(list==null || list.isEmpty()) {
				request.setAttribute("message", "No records available!");
			}else {
				request.setAttribute("orderList", list);
			}

			RequestDispatcher rdx = request.getRequestDispatcher("displayAllOrders.jsp");
            rdx.forward(request, response);
            break;
		default:
			response.sendRedirect("error.html");
			break;
		}
	}

}
