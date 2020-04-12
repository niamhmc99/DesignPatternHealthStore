<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Add Item </title>

<link rel="stylesheet"
	href="<c:url value="/resource/bootstrap/css/bootstrap.min.css"/>">
	<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/register.css"/>">
	<script src="<c:url value="/resource/bootstrap/js/bootstrap.min.js"/>"></script>
	<link rel="icon" type="image/x-icon" href="<c:url value="/resource/images/favicon1.png"/>" />
	
</head>
<body>


	<%@ include file="navBar.jsp"%>
	<div class="container" style="margin-bottom:19px">
		<h1 class="well">Add Item!</h1>
		<div class="col-lg-12 well">
			<div class="row">
			
			<c:url value="/item/addItem" var="url"></c:url>
				<form:form method="post" action="${url}" modelAttribute="itemFormObj" enctype="multipart/form-data">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<form:label path="title">Items Title</form:label>
								 <form:input type="text"
									placeholder="Enter Item's Title.." class="form-control"
									path="title"></form:input>
								 <form:errors path="title"></form:errors>
							</div>
						</div>
						<div class="form-group">
						<form:label path="category">Item's Category</form:label>
								 <form:radiobutton path="category" value="Vitamins "/>Vitamins
								 <form:radiobutton path="category" value="Food Supplements"/>Food Supplements
								 <form:radiobutton path="category" value="Organic"/>Organic
								 <form:radiobutton path="category" value="Protein"/>Protein
						</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<form:label path="manufacturer">Item's Manufacturer</form:label>
								 <form:input type="text"
									placeholder="Enter Manufacurer.." class="form-control"
									path="manufacturer"></form:input>
							</div>
							<div class="col-sm-4 form-group">
								<form:label path="price">Item's Price</form:label>
								 <form:input type="text"
									placeholder="Enter Price.." class="form-control"
									path="price"></form:input>
								 <form:errors path="price"></form:errors>
							</div>
							<div class="col-sm-4 form-group">
								<form:label path="available">Available</form:label>
								<form:input type="long"
									placeholder="Stock Availability" class="form-control"
									path="available"></form:input>
								 
							</div>
						</div>
					 	<div class="form-group">
						<form:label path="image">Item Image</form:label>
							<form:input type="text" path="image"/>
						</div>  
						<div class="form-actions">
							<button type="submit" class="btn btn-lg btn-info">Submit</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>


</body>
</html>