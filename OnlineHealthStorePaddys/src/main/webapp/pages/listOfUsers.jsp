<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Registered Users for the Health Food Store/title>
</head>
<body>

Hello

${user}

<div layout:fragment="content" class="container mySpace">
		<form action="/trips" class="form-inline">
			<div class="form-group mb-2">
				<input type="text" class="form-control" name="city"
					placeholder="Search User" /> <input type="submit" value="Search"
					class="btn btn-primary" />
			</div>
		</form>
<div class="card">
<h1 align="center">List of Registered Users</h1>
    <br/>
    <table border="1" cellpadding="10">
        <tr>
            <th>Username</th>
            <th>Email</th>
        </tr>
            <c:forEach var="user" items="${users}">
        <tr>
        		<li>${user}</li>
            <td>${user.username}</td>
            <td>${user.email}</td>
   <%--          <td>${user.address}</td> --%>
        </tr>    
        </c:forEach> 
      <%--   <tbody>
        	<tr th:each="user:${users}">
				<td th:text="${user.email}"></td>
				<td th:text="${user.name}"></td>
			</tr>
        </tbody> --%>
    
    </table>
</div>
</div>
</body>
</html>