<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
<div class="detailCard">
	<img src="${destination.pic}" alt="Picture of ${destination.name}">
	<div>
		<div class="marginLeft">
		<h2><c:out value="${destination.name}"/></h2>
			<p><c:out value="${destination.description}"/></p>
			<p><strong>Best time of year to visit:</strong> <c:out value="${destination.timeOfYear}"/></p>
			<p><strong>How expensive on average?</strong> <c:out value="${destination.expensive}"/></p>
			<p><strong>Type of currency:</strong> <c:out value="${destination.currency}"/></p>
			<p><strong>Visa status:</strong> <c:out value="${destination.visaStatus}"/></p>
				<c:if test="${desOnList != null}">
					<p><strong>My Travel Status:</strong> <c:out value="${desOnList.myStatus}"/></p>
					<p><strong>My notes:</strong> </p>
					<div class="notesBox">
						<p><c:out value="${desOnList.note}"/></p>
					</div>
					<button onclick="showEditForm()">Edit Notes</button>
					<form action="/bucketlist/${desOnList.id}/delete" method="post" class="deleteForm">
						<input type="hidden" name="_method" value="delete">
						<input type="submit" value="Remove From My Bucket List" class="delete">
					</form>
				</c:if>
				<c:if test="${user != null && desOnList == null}">
					<p><span onclick="showForm()" class="add"><strong>+</strong> Add to my bucket list</span></p>
				</c:if>
		</div>
	</div>
</div>
<c:if test="${user.isAdmin}">
	<div class="seeMore">
	<a href="/destinations/${destination.id}/edit"><button>Edit Destination</button></a>
	<form action="/destinations/${destination.id}/delete" method="post" class="deleteForm">
		<input type="hidden" name="_method" value="delete">
		<input type="submit" value="Delete Destination" class="delete">
	</form>
	</div>
</c:if>
	<form:form action="/bucketlist/add/${destination.id}" method="post" modelAttribute="bucketList" id="addToBucketList">
	<p class="rightAlign" onclick="hideForm()">X</p>
		<div class="formInputs">
			<form:label path="myStatus">My Travel Status:</form:label>
			<form:errors path="myStatus"/>
			<form:select path="myStatus">
				<form:option value=""> </form:option>
				<form:option value="Dreaming">Dreaming</form:option>
				<form:option value="Planning">Planning</form:option>
				<form:option value="Been there!">Been there!</form:option>
			</form:select>
		</div>
		<div class="formInputs">
			<form:label path="note">Notes:</form:label>
			<form:errors path="note"/>
			<form:textarea path="note"></form:textarea>
		</div>
		<form:errors path="user"/>
		<form:input type="hidden" path="user" value="${user.id}"/>
		<form:errors path="destination"/>
		<form:input type="hidden" path="destination" value="${destination.id}"/>
		<input type="submit">
	</form:form>
	<c:if test="${desOnList != null}">
	<form:form action="/bucketlist/${desOnList.id}/edit" method="post" modelAttribute="desOnList" id="editToBucketList">
	<input type="hidden" name="_method" value="put">
	<p class="rightAlign" onclick="hideEditForm()">X</p>
		<div class="formInputs">
			<form:label path="myStatus">My Travel Status:</form:label>
			<form:errors path="myStatus"/>
			<form:select path="myStatus">
				<form:option value=""> </form:option>
				<form:option value="Dreaming">Dreaming</form:option>
				<form:option value="Planning">Planning</form:option>
				<form:option value="Been there!">Been there!</form:option>
			</form:select>
		</div>
		<div class="formInputs">
			<form:label path="note">Notes:</form:label>
			<form:errors path="note"/>
			<form:textarea path="note"></form:textarea>
		</div>
		<form:errors path="user"/>
		<form:input type="hidden" path="user" value="${user.id}"/>
		<form:errors path="destination"/>
		<form:input type="hidden" path="destination" value="${destination.id}"/>
		<input type="submit">
	</form:form>
	</c:if>
	<script src="/js/script.js"></script>
</body>
</html>