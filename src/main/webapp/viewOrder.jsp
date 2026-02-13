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
    <input type="hidden" name="operation" value="viewRecord">

    Enter Customer Name:
    <input type="text" name="customerName">
    Enter Order Date:
    <input type="date" name="orderDate">
    <input type="submit" value="View Order">
</form>
</body>
</html>