<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

	
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="UTF-8">
<title>View Shopping Cart</title>
</head>
<body>

 <jsp:include page="welcome.jsp" />
<div layout:fragment="content" class="container mySpace">

		
		<security:authorize access="hasAnyRole('ROLE_USER')"> 
			<spring:url value="/placeOrder" var="placeOrder" /> 
	
			<button class="btn btn-primary" 
                   onclick="location.href='${placeOrder}'"><h3>Checkout</h3></button>
                   <li> Discount's applied at checkout: </li>
                   <li> &euro;5 off if you spend over &euro;90</li>
                   <li> Spend over &euro;100 receive 20&#37; Off</li>
		</security:authorize>

		<div>
		<div class="card">
			<div class="card card-body">
				<table class="table table-hover" border="1" cellpadding="10">
					<thead>
						<tr>
							<th>Title</th>
							<th>Category</th>
							<th>Manufacturer</th>
							<th>Quantity</th>
							<th>Remove Item</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${cartItems}" var="cartItem">
					<tr>
							<td>${cartItem.item.title}</td>
 							<td>${cartItem.item.category}</td>
							<td>${cartItem.item.manufacturer}</td>
							<td>${cartItem.quantity}</td>
							<td>			
							<spring:url value="/shoppingCart/removeCartItem/${cartItem.cartItemId}" var="removeCartItemUrl" /> 
	
							<button class="btn btn-primary" 
                                          onclick="location.href='${removeCartItemUrl}'">Remove Cart Item</button></td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		</div>
		<div>
		
			<spring:url value="/shoppingCart/removeAllItems/${shoppingCart.shoppingCartId}" var="removeAllCartItems" /> 
	
			<button class="btn btn-primary" 
                   onclick="location.href='${removeAllCartItems}'"><h3>Remove All Items from the Cart</h3></button>
		</div>
		<div class="container">
			<h2>Your Cart Total is: Euro <td>${total}</td></h2>
		</div>

	</div>

</body>
</html>