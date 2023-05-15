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
	<div class="parent">
		<div class="imgHeight">
			<img src="/img/AdobeStock_323363323.jpeg" alt="Girl standing on beach" class="imgInContainer">
		</div>
		<div class="text">
			<img src="/img/Travel_Bucket_List.png" alt="Travel Bucket List logo" class="logo">
			<c:if test="${user == null}">
			<p><a href="/login"><button>Login</button></a></p>
			</c:if>
			<c:if test="${user != null}">
			<p><a href="/dashboard"><button>My Dashboard</button></a></p>
			</c:if>
		</div>
	</div>
	<h1 class="seeMore">Welcome to Travel Bucket List!</h1>
	<p class="homeContent">Everyone dreams of all the places they want to go. Big or small, we'll help you get there.
	We know it can be difficult to keep track of all the amazing places in the world you could go.
	And when you get your chance to go somewhere, how do you pick which one?
	That's where Travel Bucket List comes into play. Easily explore destinations and add them to your bucket list.
	Your dashboard will show you relevant information to help you make your plans. You will see things 
	like when the best time of year to visit is, how expensive the destination is on average, and whether or not you need a visa to go. 
	Sign up today to get started!</p>
	<c:if test="${user == null}">
		<p class="seeMore"><a href="/login"><button>Sign Up</button></a></p>
	</c:if>
	<div class="homeContent">
	<h2>Explore Destinations</h2>
	<div class="flex spBetween">
		<c:forEach var="destination" items="${destinations}">
			<div class="homeImgTile">
				<a href="/destinations/${destination.id}"><img src="${destination.pic}" class="homeImage"></a>
				<div class="middle">
					<div class="homeTileText">${destination.name}</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="seeMore"><h4><a href="/destinations">See more</a></h4></div>
	</div>
	<div class="homeColored">
		<div class="homeContent noMargin noPadding">
			<div class="flex noWrap noMargin">
				<img src="/img/screenshots.png" alt="screenshots of dashboard" class="width65">
				<div class="width30">
				<h2>Research and Build Your List</h2>
				<p>Research your destinations to help you easily make a decision of where and when to go and be able to plan your budget accordingly!</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>