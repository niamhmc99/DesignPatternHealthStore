<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <meta charset="utf-8">
    <title>Create an account</title>
</head>
<body>

  <jsp:include page="navBar.jsp" />


  <div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <div class="site-name">Online Health Food Store</div>
        

        <h2>Welcome <a href="${pageContext.request.contextPath}/accountInfo">${pageContext.request.userPrincipal.name} 
        <a href="${pageContext.request.contextPath}/accountInfo"> </a>  ||      
        
        <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
  </div>
  
  
  
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">

  <div class="collapse navbar-collapse" id="navbarColor01">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item"    sec:authorize="!isAuthenticated()">
        <a class="nav-link" href="/register">Register</a>
      </li>
      <li class="nav-item"  sec:authorize="!isAuthenticated()">
        <a class="nav-link" href="/login">Log in</a>
      </li>
      <li class="nav-item"   sec:authorize="isAuthenticated()"  >
        <a class="nav-link" href="/profile">Profile</a>
      </li>
        <li class="nav-item" sec:authorize="hasRole('ROLE_ADMIN')">
        <a class="nav-link" href="/users">Users</a>
        </li>
        <li class="nav-item" sec:authorize="hasRole('ROLE_ADMIN')">
        <a class="nav-link" href="/addItem">Add Item</a>
        </li>
        <li class="nav-item">
        <a class="nav-link" href="/getAllItems">View All Items</a>
      </li>
      <li class="nav-item"  sec:authorize="hasRole('ROLE_USER')">
        <a class="nav-link" href="/viewShoppingCart">View Cart</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" th:action="@{/logout}" method="post"  sec:authorize="isAuthenticated()">
      <input class="form-control mr-sm-2" type="hidden" /> 
        <h2><a onclick="document.forms['logoutForm'].submit()"><u>Logout</u></a></h2>
	</form>
  </div>
</nav>
 <img src="/resources/images/healthy_living.jpg" var="logo">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>