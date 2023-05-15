<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Travel Bucket List Login</title>
</head>
<body class="blueBackground">
	<nav class="navBar">
		<img src="/img/Travel_Bucket_List_White.png">
		<div>
			<a href="/">Home</a>&emsp;|&emsp;<a href="/destinations">Destinations</a>
		</div>
	</nav>
	<div class="flex">
	<div class="formStyle">
		<h2>Register</h2>
		<form:form action="/register" method="post" modelAttribute="newUser">
			<div class="formInputs">
				<form:label path="name">Name: </form:label>
				<form:errors path="name" class="errors"/>
				<form:input path="name"/>
			</div>
			<div class="formInputs">
				<form:label path="email">Email: </form:label>
				<form:errors path="email" class="errors"/>
				<form:input path="email"/>
			</div>
			<div class="formInputs">
				<form:label path="password">Password: </form:label>
				<form:errors path="password" class="errors"/>
				<form:password path="password"/>
			</div>
			<div class="formInputs">
				<form:label path="confirm">Confirm Password: </form:label>
				<form:errors path="confirm" class="errors"/>
				<form:password path="confirm"/>
			</div>
			<input type="submit" class="submit">
		</form:form>
	</div>
	<div class="formStyle">
	<h2>Login</h2>
	<form:form action="/login" method="post" modelAttribute="newLogin">
		<div class="formInputs">
			<form:label path="email">Email: </form:label>
			<form:errors path="email" class="errors"/>
			<form:input path="email"/>
		</div>
		<div class="formInputs">
			<form:label path="password">Password: </form:label>
			<form:errors path="password" class="errors"/>
			<form:password path="password"/>
		</div>
		<input type="submit" class="submit">
	</form:form>
	</div>
	</div>
</body>
</html>