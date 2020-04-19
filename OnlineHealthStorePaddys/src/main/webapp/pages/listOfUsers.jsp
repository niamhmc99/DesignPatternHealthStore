<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>All Registered Users for the Health Food Store</title>
</head>
<body>


 <jsp:include page="welcome.jsp" />

	<div layout:fragment="content" class="container mySpace">
			<div class="form-group mb-2">
			  <input type="text" id="myInput" onkeyup="searchUsers()" placeholder="Search for names.." title="Type in a name"/>
			</div>

	<div class="card">
		<div class="card card-body">
		<h1 align="center">List of Registered Users</h1>
    		<table class="table table-hover" border="1" cellpadding="10" id ="userList">
    			<thead>
       				 <tr>
            			<th>Username</th>
            			<th>Email</th>
            			<th>Address</th>
            			<th>
            			<security:authorize
							 access="hasAnyRole('ROLE_ADMIN')">
					 	View Order History
					</security:authorize>
					</th>
        			</tr>
    			</thead>
    			<tbody>
    		<c:forEach items="${users}" var="user">
    	
        		<tr th:each="user:${users}"> 
		            <td>${user.username}</td>
            		<td>${user.email}</td>
             		<td>${user.address}</td>
             		<td>
             		<security:authorize access="hasAnyRole('ROLE_ADMIN')"> 
					<spring:url value="/orderHistory/${user.userId}" var="orderHistoryUrl" /> 
	
						<button class="btn btn-primary" 
                                          onclick="location.href='${orderHistoryUrl}'">View</button>
					</security:authorize>
					</td>
				
        		</tr>  
        		</c:forEach>  
    			</tbody>
    		</table>
    	</div>
	</div>
	</div>
	<script>
	function searchUsers() {
  		var input, filter, table, tr, td, i, txtValue;
  		input = document.getElementById("myInput");
  		filter = input.value.toUpperCase();
  		table = document.getElementById("userList");
  		tr = table.getElementsByTagName("tr");
  		th = table.getElementsByTagName("th")
  		
  		for (i = 1; i < tr.length; i++) {

          	tr[i].style.display = "none";
  				for(var j= 0 ; j<th.length;j++)
  	  		    {
    				td = tr[i].getElementsByTagName("td")[j];

    				if (td) 
    				{
      					txtValue = td.textContent || td.innerText;
      					if (txtValue.toUpperCase().indexOf(filter) > -1) 
          				{
       					 tr[i].style.display = "";
        				break;
      					} 
    				} 
    			}
  			}
		}
	</script>
	
</body>
</html>