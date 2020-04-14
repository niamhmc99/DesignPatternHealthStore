<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
 <html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorate="~{fragments/main_layout}"> 
	
	
<%@ include file="navBar.jsp"%>
<head>
<meta charset="UTF-8">
<title>Item Management</title>
<%-- <link rel="icon" type="image/x-icon"
	href="<c:url value="/resources/images/healthy_living.jpg"/>" /> --%>
	
	<c:url value="/webapp/WEB-INF/resource/images/healthy_living.jpg" var="logo"/>
	
<link rel="stylesheet" type="text/css"
	href="<c:url value="../resource/css/itemList.css"/>">
</head>

<body>

<%-- <img src="${pageContext.request.contextPath}/resource/images/healthy_living.jpg">
 --%>
 <img src="/webapp/WEB-INF/resource/images/healthy_living.jpg">
 
	<div class="container" id="itemTable" layout:fragment="content"
		style="width: 1145px; margin-bottom: 180px;">
		<h2>Item Management</h2>
		<p>The List of Items in the Health Food Store</p>
		
		<security:authorize access="hasAnyRole('ROLE_ADMIN')"> 
			<spring:url value="/item/addItem" var="addItemUrl" /> 
	
			<button class="btn btn-primary" 
                   onclick="location.href='${addItemUrl}'">Add Item</button>
		</security:authorize>
			
		<table class="table table-hover" border="1" cellpadding="10" id="itemList">
			<thead>
				<tr>
					<th>Image </th>
					<th>Id</th>
					<th>Items Title</th>
					<th>Category</th>
					<th>Items Price</th>
					<th>Manufacturer</th>
					
					<th>View
					 <security:authorize access="hasAnyRole('ROLE_USER')">
					/ Add to Cart
					</security:authorize> 
					<security:authorize
							 access="hasAnyRole('ROLE_ADMIN')">
					 /Edit /Delete 
					</security:authorize>
					</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${items}" var="item">
					<tr>
						<td style="width: 171px"><img
							src="<c:url value="/resource/images/items/${item.itemId}.jpg"/>"
							style="width: 100px; height: 90px;  ${item.title}"/></td>
						<td>${item.itemId}</td>
						<td>${item.category}</td>
						<td>${item.title}</td>
						<td>${item.price}</td>
						<td>${item.manufacturer}</td>
						
						
						<td> 
						<spring:url value="/viewCart" var="viewCartUrl" /> 
	
						<button class="btn btn-primary" 
                                          onclick="location.href='${viewCartUrl}'">View Cart</button>
						<security:authorize access="hasAnyRole('ROLE_USER')"> 
							<spring:url value="/shoppingCart/add/${item.itemId}" var="addToCartUrl" /> 
	
						<button class="btn btn-primary" 
                                          onclick="location.href='${addToCartUrl}'">Add To Cart</button>
						</security:authorize>
						<security:authorize access="hasAnyRole('ROLE_ADMIN')"> 
							<spring:url value="/items/${item.itemId}/delete" var="deleteUrl" /> 
				  			<spring:url value="/item/editItem/${item.itemId}" var="updateUrl" />
	
						<button class="btn btn-primary" 
                                          onclick="location.href='${updateUrl}'">Update</button>
				  		<button class="btn btn-danger" 
                                        onclick="location.href='${deleteUrl}'">Delete</button>
                       </security:authorize>
                        </td>   
                      
        
					</tr>
				</c:forEach>
			</tbody>
			
		
		
		
		
		</table>
	
	
	
	</div>

</body>
</html>