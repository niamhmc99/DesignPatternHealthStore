<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorate="~{fragments/main_layout}">
<head>
<meta charset="UTF-8">
<title>View Shopping Cart</title>
</head>
<body>
<div layout:fragment="content" class="container mySpace">
		<form action="/items" class="form-inline">
			<div class="form-group mb-2">
				<input type="text" class="form-control" name="title"
					placeholder="Search Item Title" /> <input type="submit" value="Search"
					class="btn btn-primary" />
			</div>
		</form>
		
		<form action="/placeOrder" class="form-inline">
			<div class="form-group mb-2">
				<input type="submit" value="Place Order" class="btn btn-primary" />
			</div>
		</form>
		
		<div class="card">
			<div class="card card-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Category</th>
							<th>Manufacturer</th>
							<th>Title</th>
							<th>Quantity</th>
						</tr>
					</thead>
					<tbody>
						<tr th:each="cartItem:${cartItems}">
							<td th:text="${cartItem.item.category}"></td>
							<td th:text="${cartItem.item.manufacturer}"></td>
							<td th:text="${cartItem.item.title}"></td>
							<td th:text="${cartItem.quantity}"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="container">
			<h2>Your Cart Total is: Euro <span th:utext="${total}"></span></h2>
		</div>
		
	</div>

</body>
</html>