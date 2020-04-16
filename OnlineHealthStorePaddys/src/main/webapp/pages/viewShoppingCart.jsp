<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	
<%@ include file="navBar.jsp"%>
	
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="UTF-8">
<title>View Shopping Cart</title>
</head>
<body>
<div layout:fragment="content" class="container mySpace">
		<form action="getItemById/${item.itemId}" class="form-inline">
			<div class="form-group mb-2">
				<input type="text" class="form-control" name="title"
					placeholder="Search Item Title" /> <input type="submit" value="Search"
					class="btn btn-primary" />
			</div>
		</form>
		
		<form action="/placeOrder" class="form-inline">
			<div class="form-group mb-2">
				<input type="submit" value="Place Order" class="btn btn-primary" />
			</div>
		</form>
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
		<security:authorize access="hasAnyRole('ROLE_USER')"> 
			<spring:url value="/shoppingCart/removeAllItems/${shoppingCartId}" var="removeAllCartItems" /> 
	
			<button class="btn btn-primary" 
                   onclick="location.href='${removeAllCartItems}'"><h3>Remove All Items from the Cart</h3></button>
		</security:authorize>
		</div>
		<div class="container">
			<h2>Your Cart Total is: Euro <td>${total}</td></h2>
		</div>

	</div>

</body>
</html>