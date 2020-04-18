<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="navBar.jsp"%>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Order Details</title>
</head>
<body>
<div layout:fragment="content" class="container mySpace">

		<div class="container">
			<h2>
				Your Cart Total is: Euro  <td>${total}</td>
			</h2>
		</div>
		
			<div class="container">
			<h2>
				Your Cart Total with Discount: Euro  <td>${totalWithDiscount}</td>
			</h2>
		</div>
		
		<form:form action="/placeOrder" method="POST" modelAttribute="customerOrder">
			<input type="hidden" name="total" value="${total}" />
			<div class="alert alert-danger" id="error-alert-order" role="alert"
				th:if="${errorMessage}">
				Not enough stock!
				<script>
					$("#error-alert-order").fadeTo(2000, 500).slideUp(500,
							function() {
								$("#error-alert-order").slideUp(500);
							});
				</script>
			</div>
			<div class="form-group">
				<label class="col-form-label" for="payment_method">Select
					Payment Method: </label> <select class="form-control" id="payment_method"
					name="payment_method">
					<option value="default">Select a payment method</option>
					<option value="Visa">Visa</option>
					<option value="Mastercard">Mastercard</option>
				</select> <br />
				<p>Visa must be 16 digits</p>
				<p>Mastercard must be 15 digits</p>
			</div>
			<div class="alert alert-danger" id="visa-error" role="alert"
				th:if="${visaError}">
				Visa payment failed!
				<script>
					$("#visa-error").fadeTo(2000, 500).slideUp(500, function() {
						$("#visa-error").slideUp(500);
					});
				</script>
			</div>
			<div class="alert alert-danger" id="mastercard-error" role="alert"
				th:if="${mastercardError}">
				Mastercard payment failed!
				<script>
					$("#mastercard-error").fadeTo(2000, 500).slideUp(500,
							function() {
								$("#mastercard-error").slideUp(500);
							});
				</script>
			</div>
			<div class="form-group">
				<label class="col-form-label" for="name">Card Name: </label> <input
					type="text" class="form-control" id="name" name="name"
					placeholder="Cardholder name" required autofocus />
			</div>
			<div class="form-group">
				<label class="col-form-label" for="cardNumber">Card Number:
				</label> <input type="text" class="form-control" id="cardNumber"
					name="cardNumber" placeholder="Card number" required autofocus />
			</div>
			<div class="form-group">
				<label class="col-form-label" for="expires">Expiry Date: </label> <input
					type="date" class="form-control" id="expires" name="expires"
					placeholder="Expiry date" required autofocus />
			</div>
			<button type="submit" class="btn btn-lg btn-secondary btn-block">Pay
				Now</button>
		</form:form>

	</div>

</body>
</html>