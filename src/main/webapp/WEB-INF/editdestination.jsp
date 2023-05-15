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
<title>Travel Bucket List</title>
</head>
<body class="blueBackground">
<nav class="navBar">
	<img src="/img/Travel_Bucket_List_White.png">
	<div>
		<a href="/">Home</a>&emsp;|&emsp;<a href="/destinations">Destinations</a>
		<c:if test="${user == null}">
			<a href="/login"><button class="login">Login</button></a>
		</c:if>
		<c:if test="${user != null}">
			<span>&emsp;|&emsp;<a href="/dashboard">My Dashboard</a></span>
			<a href="/logout"><button class="login">Log Out</button></a>
		</c:if>
	</div>
</nav>
	<div class="formStyle">
	<h2 class="seeMore">Edit Destination</h2>
	<form:form action="/destinations/${destination.id}/edit" method="post" modelAttribute="destination">
	<input type="hidden" name="_method" value="put">
		<div class="formInputs">
			<form:label path="name">Name: </form:label>
			<form:errors path="name" class="errors"/>
			<form:input path="name"/>
		</div>
		<div class="formInputs">
			<form:label path="pic">Picture Link: </form:label>
			<form:errors path="pic" class="errors"/>
			<form:input path="pic"/>
		</div>
		<div class="formInputs">
			<form:label path="description">Description: </form:label>
			<form:errors path="description" class="errors"/>
			<form:textarea path="description" rows="5"/>
		</div>
		<div class="formInputs">
			<form:label path="timeOfYear">Best time of year to visit: </form:label>
			<form:errors path="timeOfYear" class="errors"/>
			<form:input path="timeOfYear"/>
		</div>
		<div class="flexNoMargin">
			<div class="formInputs">
				<form:label path="expensive">How Expensive on Average? </form:label>
				<form:errors path="expensive" class="errors"/>
				<form:input path="expensive"/>
			</div>
			<div class="formInputs">
				<form:label path="currency">Currency Type: </form:label>
				<form:errors path="currency" class="errors"/>
				<form:input path="currency"/>
			</div>
		</div>
		<div class="formInputs">
			<form:label path="visaStatus">Current Visa Status: </form:label>
			<form:errors path="visaStatus" class="errors"/>
			<form:input path="visaStatus"/>
		</div>
		<input type="submit" class="submit">
	</form:form>
	</div>
</body>
</html>