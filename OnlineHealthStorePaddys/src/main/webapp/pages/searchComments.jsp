<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE>
<html>
<head>
<%@ include file="navBar.jsp"%>

<meta charset="UTF-8">
<title>View/ Submit Comments</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div id="gig-list">
		<div class="item">
			<div class="start">
				<img src="${item.image}" />
				<!-- <p>${list.id}</p> -->
				<p class="title">${item.title}</p>
			</div>
			<div class="end">
				<div class="info">
					<p class="category">${item.category}</p>
					<div class="location">
						<p class="manufacturer">${item.manufacturer}</p>
					</div>
				</div>
				<p class="price">€ ${item.price}</p>				
			</div>
			<p class="price">Reviews</p>
			<table class="table table-hover" border="1" cellpadding="10" id="comments">
			<thead>
				<tr>
					<th>Name </th>
					<th>Rating</th>
					<th>Comment</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${comments}" var="comment">
					<tr>
						<td>${comment.name} </td>
						<td>${comment.rating}</td>
						<td>${comment.comment}</td>
					</tr>
				</c:forEach>
				</tbody>
				</table>
		</div>
</div>

<div>

		
	<form:form action="/submitReview" method="POST">
		<input type="hidden" name="itemId" id="itemId" value="${item.itemId}"/>
		Leave your own review of ${item.title}
		Review
		<textarea id="comment" name="comment" autofocus rows="10" cols="50"style="font-size: 14px; font-weight: normal; resize: none; overflow-y: scroll;"></textarea>
		<br>Enter a Rating out of 5 
		<select id="rating" name="rating">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
	</select>
	<br>
	<button type="submit" class="purchase-button">Submit Review</button>
	</form:form>
	
	<div>
		<form action="/viewShoppingCart" method="GET" class="form-inline my-2 my-lg-0">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit">View Cart</button>
		</form>
		
	</div>
</div>
</body>
</html>