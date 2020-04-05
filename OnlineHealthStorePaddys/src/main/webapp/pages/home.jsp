<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

Welcome to the Online Health Store:

Add an Item to the Database:

<form action="saveOrUpdateItem">
<input type="text" name="itemId"><br>
<input type="text" name="title"><br>
<input type="text" name="manufacturer"><br>
<input type="text" name="category"><br>
<input type="number" name="price"><br>
<input type="text" name="image"><br>

<input type="submit"><br>
</form>
</body>

<form action="getItemById">
<input type="text" name="itemId"><br>
<input type="submit"><br>
</form>
</body>
</html>