<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Users Order History</title>
</head>
<body>


 <jsp:include page="welcome.jsp" />
<div class="card">
			<div class="card card-body">
				<table class="table table-hover" border="1" cellpadding="10">
					<thead>
						<tr>
							<th>User</th>
							<th>Order Id </th>
							<th>Address</th>
							<th>Order Total</th>
							<th>Payment Method</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${orders}" var="order">
					<tr>
						<td>${user.userId}</td>
						<td>${order.orderId}</td>
 						<td>${order.address}</td>
 						<td>${order.orderTotal}</td>
 						<td>${order.paymentMethod}</td>
 						
 							
							<td>			
							<spring:url value="/orderHistoryItemDetails/${order.orderId}" var="orderHistoryItemDetails" /> 
	
							<button class="btn btn-primary" 
                                          onclick="location.href='${orderHistoryItemDetails}'"> Order History Items </button></td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

</body>
</html>