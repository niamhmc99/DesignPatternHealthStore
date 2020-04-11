<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<!-- <html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorate="~{fragments/main_layout}"> -->
	
	<html>
	
<%@ include file="navBar.jsp"%>
<head>
<meta charset="UTF-8">
<title>Item Management</title>
</head>
<body>

	<div class="container" id="itemTable" layout:fragment="content"
		style="width: 1145px; margin-bottom: 180px;">
		<h2>Item Management</h2>
		<p>The List of Items in the Health Food Store</p>
		<table class="table table-hover" id="itemList">
			<thead>
				<tr>
					<th>Screen-Shot</th>
					<th>Item Id</th>
					<th>Category</th>
					<th>Item Name</th>
					<th>Items Price</th>
<!-- 					<th>Stock Unit</th>
 --><!-- 					<th>Description</th>
 -->					<th>Manufacturer</th>
					<th>View <security:authorize access="hasAnyRole('ROLE_USER')">
					/ Add to Cart
					</security:authorize> <!--views only to the admin --> 
					<security:authorize
							 access="hasAnyRole('ROLE_ADMIN')">
					/Edit/Delete
					</security:authorize>
					</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${items}" var="item">
					<tr>
						<td style="width: 171px"><img
							src="<c:url value="/resource/images/products/${item.itemId}.jpg"/>"
							style="width: 100px; height: 90px;  ${item.productName}"/></td>
						<td>${item.itemId}</td>
						<td>${item.category}</td>
						<td>${item.title}</td>
						<td>${item.price}</td>
<%-- 						<td>${item.unitStock}</td>
 --%><%-- 						<td style="width: 180px">${item.productDescription}</td>
 --%>						<td>${prod.manufacturer}</td>
						<td><a
							href="findItemById/${item.itemId}" class="btn btn-info"
							role="button"> <span class="glyphicon glyphicon-info-sign"></span></a>

							<!--view only for user -->
							 <security:authorize
								 access="hasAnyRole('ROLE_USER')">
								<a href="#" ng-click="addToCart(${item.itemId})"
									class="btn btn-primary" style="margin-left: 5px"> <span
									class="glyphicon glyphicon-shopping-cart"></span></a>
							</security:authorize> <!-- 						view only to the admin --> <security:authorize
								access="hasAnyRole('ROLE_ADMIN')">
								<a href="admin/product/editProduct/${item.itemId}"
									class="btn btn-success" style="margin-left: 5px"> <span
									class="glyphicon glyphicon-edit"></span></a>
								<a href="admin/delete/${item.itemId}" class="btn btn-danger"
									style="margin-left: 5px"> <span
									class="glyphicon glyphicon-trash"></span></a>
							</security:authorize></td>
					</tr>
				</c:forEach>
			</tbody>
			
		
		
		
		
		</table>
	
	
	
	</div>

</body>
</html>