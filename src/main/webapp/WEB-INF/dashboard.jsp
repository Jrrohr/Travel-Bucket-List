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
		<a href="/logout"><button class="login">Log Out</button></a>
	</div>
</nav>
<h2 class="seeMore">My Travel Bucket List</h2>
	<table class="table dashTable sortable">
		<thead>
			<tr class="blueBackground">
				<th class="pointer">Pic</th>
				<th class="pointer">Destination</th>
				<th class="pointer">My Status</th>
				<th class="pointer">Best Time of Year</th>
				<th class="pointer">Visa Status</th>
				<th class="pointer">How Expensive?</th>
				<th class="pointer">Currency</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${bucketList}">
			<tr>
				<td><div><img src="${item.destination.pic}" class="tableIcon"></div></td>
				<td><a href="/destinations/${item.destination.id}"><c:out value="${item.destination.name}"/></a></td>
				<td><c:out value="${item.myStatus}"/></td>
				<td><c:out value="${item.destination.timeOfYear}"/></td>
				<td><c:out value="${item.destination.visaStatus}"/></td>
				<td><c:out value="${item.destination.expensive}"/></td>
				<td><c:out value="${item.destination.currency}"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<h3 class="seeMore"><a href="/destinations">Explore More Destinations</a></h3>
<script src="https://www.kryogenix.org/code/browser/sorttable/sorttable.js"></script>
</body>
</html>