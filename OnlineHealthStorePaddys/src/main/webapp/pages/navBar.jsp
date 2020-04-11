<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorate="~{fragments/main_layout}">
	
<head>
<meta charset="UTF-8">
<title>Navigation Bar</title>
</head>
<body>

	<nav class="navbar navbar-inverse">
	<div class="container-fluid">

<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href=" <c:url value="/index1" />">Home</a></li>
				<li><a href=" <c:url value="/getAllProducts" />">Product
						List</a></li>
				<li><a href=" <c:url value="/aboutus" />">About Us</a></li>
				
				<!-- access="hasRole('ROLE_USER') -->
				<security:authorize access="hasRole('ROLE_USER')">
				<li><a href=" <c:url value="/contactus" />">Contact Us</a></li>
				</security:authorize>
				
				<!-- 			Only admin can view this link -->
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href=" <c:url value="/admin/product/addProduct" />">Add
							Product</a></li>
				</security:authorize>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">

				<c:if test="${!empty pageContext.request.userPrincipal.name}">
					<li><a href="<c:url value="/index1" />"><span
							class="glyphicon glyphicon-shopping-user"></span>Welcome..${pageContext.request.userPrincipal.name}</a></li>

					<security:authorize access="hasRole('ROLE_USER')">
						<li><a href="<c:url value="/cart/getCartById" />"><span
								class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
					</security:authorize>
					<li><a href="<c:url value="/logout" />"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</c:if>
			</ul>
					<ul class="nav navbar-nav navbar-right">

				<c:if test="${pageContext.request.userPrincipal.name==null}">
					<li><a href="<c:url value="/login" />"><span
							class="glyphicon glyphicon-shopping-cart"></span>My Cart</a></li>
					<li><a href="<c:url value="/customer/registration" />"><span
							class="glyphicon glyphicon-log-user"></span> SignUp</a></li>
					<li><a href="<c:url value="/login" />"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</c:if>
			</ul>
</div>
</div>
</nav>

</body>
</html>