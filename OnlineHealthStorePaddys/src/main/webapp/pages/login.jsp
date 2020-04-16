<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>

<html lang="en">
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

      <meta charset="utf-8">
      <title>Log in with your account</title>
</head>

	<body>
    	<div class="container">
     	 <form method="POST" action="${contextPath}/login" class="form-signin">
     	   <h2 class="form-heading">Log in</h2>

      	  <div class="form-group ${error != null ? 'has-error' : ''}">
      	      <span>${message}</span>
       	     <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
       	     <input name="password" type="password" class="form-control" placeholder="Password"/>
           	 <span>${error}</span>
           	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	
           	 <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
        </div>
      </form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/static/js/bootstrap.min.js"></script> 
	</body>
</html>