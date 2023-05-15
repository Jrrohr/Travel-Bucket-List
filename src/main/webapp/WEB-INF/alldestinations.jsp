<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Travel Bucket List</title>
</head>
<body>
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
	<h2 class="seeMore">All Destinations</h2>
	<c:if test="${user.isAdmin}">
		<p class="seeMore"><a href="/destinations/new">Add new</a></p>
	</c:if>
	<div class="flex homeContent">
		<c:forEach var="destination" items="${destinations}">
		<div class="seeMore">
			<div class="homeImgTile">
				<a href="/destinations/${destination.id}"><img src="${destination.pic}" class="homeImage"></a>
				<div class="middle">
					<div class="homeTileText">${destination.name}</div>
				</div>
			</div>
			<h5>${destination.name}</h5>
		</div>
		</c:forEach>
	</div>
</body>
</html>