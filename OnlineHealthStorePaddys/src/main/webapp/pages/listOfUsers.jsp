<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorate="~{fragments/main_layout}">
	
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>All Registered Users for the Health Food Store</title>
</head>
<body>

<jsp:include page="header.jsp" />

	<div layout:fragment="content" class="container mySpace">
		<form action="/users" class="form-inline">
			<div class="form-group mb-2">
				<input type="text" class="form-control" name="username"
					placeholder="Search User" /> <input type="submit" value="Search"
					class="btn btn-primary" />
			</div>
		</form>
	<div class="card">
		<div class="card card-body">
		<h1 align="center">List of Registered Users</h1>
    		<table class="table table-hover" border="1" cellpadding="10">
    			<thead>
       				 <tr>
            			<th>Username</th>
            			<th>Email</th>
            			<th>Address</th>
        			</tr>
    			</thead>
    			<tbody>
    		<c:forEach items="${users}" var="user">
    	
        		<tr th:each="user:${users}"> 
		            <td>${user.username}</td>
            		<td>${user.email}</td>
             		<td>${user.address}</td>
        		</tr>  
        		</c:forEach>  
    			</tbody>
    		</table>
    	</div>
	</div>
	</div>
</body>
</html>