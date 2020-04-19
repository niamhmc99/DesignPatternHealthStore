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
<title>Item Management</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<body>
 <jsp:include page="welcome.jsp" />
 
	<div class="container" id="itemTable" layout:fragment="content"
		style="width: 1145px; margin-bottom: 180px;">
		<h2>Item Management</h2>
		<p>The List of Items in the Health Food Store</p>
		
			<div class="form-group mb-2">
					 <input type="text" id="myInput" onkeyup="searchItems()" placeholder="Search.." title="Type in a name"/>
			</div>
		
		
		<security:authorize access="hasAnyRole('ROLE_ADMIN')"> 
			<spring:url value="/item/addItem" var="addItemUrl" /> 
	
			<button class="btn btn-primary" 
                   onclick="location.href='${addItemUrl}'">Add Item</button>
		</security:authorize>
			
		<security:authorize access="hasAnyRole('ROLE_USER')"> 
			<spring:url value="/viewShoppingCart" var="viewShoppingCart" /> 
	
			<button class="btn btn-primary" 
                   onclick="location.href='${viewShoppingCart}'">View Shopping Cart</button>
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
						<td>${item.title}</td>
						<td>${item.category}</td>
						<td>${item.price}</td>
						<td>${item.manufacturer}</td>
						
						<td> 
						<spring:url value="/getItemById/${item.itemId}" var="viewItemUrl" /> 
	
						<button class="btn btn-primary" 
                                          onclick="location.href='${viewItemUrl}'">View Item</button>
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
		<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')"> 
		<form action="/AscendingByName" method="GET" class="form-inline my-2 my-lg-0">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit">Filter By Item Title A-Z</button>
		</form>
		<form action="/DecendingByName" method="GET" class="form-inline my-2 my-lg-0">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit">Filter By Item Title Z-A</button>
		</form>
		<form action="/AscendingByPrice" method="GET" class="form-inline my-2 my-lg-0">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit">Filter By Price 0-100</button>
		</form>
		<form action="/DecendingByPrice" method="GET" class="form-inline my-2 my-lg-0">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit">Filter By Price 100-0</button>
		</form>
		<form action="/AscendingByCategory" method="GET" class="form-inline my-2 my-lg-0">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit">Filter By Category A-Z</button>
		</form>
		<form action="/DecendingByCategory" method="GET" class="form-inline my-2 my-lg-0">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit">Filter By Category Z-A</button>
		</form>
		<form action="/AscendingByManufacturer" method="GET" class="form-inline my-2 my-lg-0">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit">Filter By Manufacturer A-Z</button>
		</form>
		<form action="/DecendingByManufacturer" method="GET" class="form-inline my-2 my-lg-0">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit">Filter By Manufacturer Z-A</button>
		</form>
		</security:authorize>
		
		<script>
	function searchItems() {
  		var input, filter, table, tr, td, i, txtValue;
  		input = document.getElementById("myInput");
  		filter = input.value.toUpperCase();
  		table = document.getElementById("itemList");
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