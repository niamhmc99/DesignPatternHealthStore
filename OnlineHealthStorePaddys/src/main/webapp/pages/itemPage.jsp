<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page isELIgnored="false"%>

<%@ include file="navBar.jsp"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Items</title>
<link rel="icon" type="image/x-icon" href="<c:url value="/resource/images/favicon1.png"/>" />

</head>
<body>
	<div>
		<div class="container" style="width: 829px">
			<h2>Item Details</h2>
			<p>Details of the Item</p>
			<table class="table table-bordered" id="item">
				<tbody>

					<%-- <tr>
						<td>Item Image</td>
						<td><img
							src="<c:url value="/resource/images/items/${itemObj.itemId}.jpg"/>"
							width="40%" alt="${itemObj.title}" /></td>
					</tr> --%>
					<tr>
						<td>Item ID</td>
						<td>${itemObj.itemId }</td>
					</tr>
					<tr>
						<td>Item's Title</td>
						<td>${itemObj.title }</td>
					</tr>
					<tr>
						<td>Item Category</td>
						<td>${itemObj.category}</td>
					</tr>
					<tr>
						<td>Item's Manufacturer</td>
						<td>${itemObj.manufacturer}</td>
					</tr>
					<tr>
						<td>Price</td>
						<td>${itemObj.price}</td>
					</tr>
					<tr>
						<td>Stock Available</td>
						<td>${itemObj.available}</td>
					</tr>
					<tr>
						<td>Add to Cart:</td>
						<td><c:url value="/shoppingCart/add/(${itemObj.itemId})"
								var="addcart"></c:url>
							<div>
							
							<security:authorize access="hasAnyRole('ROLE_USER')"> 
							<spring:url value="/shoppingCart/add/${itemObj.itemId}" var="addToCartUrl" /> 
							<button class="btn btn-primary" 
                                          onclick="location.href='${addToCartUrl}'">Add Item</button>
                             </security:authorize>
								<form:form action="/viewComments" method="POST">
									<input type="hidden" name="itemId" id="itemId" value="${itemObj.itemId}" />
									<button type="submit" class="purchase-button">View Comments/Reviews</button>
							</form:form>
								<div>
							        <a class="nav-link" href="/getAllItems">Continue Shopping</a>
								
								</div>
				
							</div></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>