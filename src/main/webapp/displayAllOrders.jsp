<%@ page import="java.util.*, com.wipro.order.bean.OrderBean" %>
<html>
<body>

<%
    String msg = (String) request.getAttribute("message");
    List<OrderBean> list = (List<OrderBean>) request.getAttribute("orderList");
%>

<% if (msg != null) { %>
    <h3><%= msg %></h3>
<% } else { %>
    <h3>All Orders</h3>
    <table border="1">
        <tr>
            <th>Order ID</th>
            <th>Customer</th>
            <th>Item</th>
            <th>Status</th>
        </tr>
        <% for (OrderBean o : list) { %>
        <tr>
            <td><%= o.getOrderId() %></td>
            <td><%= o.getCustomerName() %></td>
            <td><%= o.getOrderItem() %></td>
            <td><%= o.getStatus() %></td>
        </tr>
        <% } %>
    </table>
<% } %>

</body>
</html>
