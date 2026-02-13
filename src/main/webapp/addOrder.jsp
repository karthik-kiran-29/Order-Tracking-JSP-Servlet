<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="MainServlet" method="post">
		<input type="hidden" name="operation" value="newRecord">
		
		Order ID: <input type="text" name="orderId"><br>
    	Customer Name: <input type="text" name="customerName"><br>
    	Order Item: <input type="text" name="orderItem"><br>
    	Order Date: <input type="date" name="orderDate"><br>
    	Delivery Date: <input type="date" name="deliveryDate"><br>
    	Status: <input type="text" name="status"><br>
    	Remarks: <input type="text" name="remarks"><br><br>

    	<input type="submit" value="Add Order">
	</form>

</body>
</html>