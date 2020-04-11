<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <jsp:include page="navBar.jsp" />


  <div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <div class="site-name">Online Health Food Store</div>
        

        <h2>Welcome <a href="${pageContext.request.contextPath}/accountInfo">${pageContext.request.userPrincipal.name} <a href="${pageContext.request.contextPath}/accountInfo"> </a>  ||      
        
        <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
  </div>
  
  
  
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
<!--   <a class="navbar-brand" href="#">Online Health Food Store</a>
  
 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">-->
 <!--    <span class="navbar-toggler-icon"></span>
  </button> -->

 

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
        <a class="nav-link" href="/viewCart">View Cart</a>
      </li>
   
      <!-- </li>  <li class="nav-item">
        <a class="nav-link" href="/sample">About</a>
      </li>
      <li class="nav-item"   sec:authorize="isAuthenticated()"  >
        <a class="nav-link" href="/events">Events</a>
      </li>
      <li class="nav-item"   sec:authorize="hasRole('ROLE_USER')"  >
        <a class="nav-link" href="/trips">Trips</a>
      </li> -->
    </ul>
    <form class="form-inline my-2 my-lg-0" th:action="@{/logout}" method="post"  sec:authorize="isAuthenticated()">
      <input class="form-control mr-sm-2" type="hidden" /> 
        <h2><a onclick="document.forms['logoutForm'].submit()"><u>Logout</u></a></h2>
<!--       <button class="btn btn-secondary my-2 my-sm-0" type="submit">Logout</button>-->    
	</form>
  </div>
</nav>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>