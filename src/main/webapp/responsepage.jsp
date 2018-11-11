<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Result of query</title>
</head>
<body>


</br>
<h2>Showing content from database on selection of: ${media} with sorting on: ${sort}</h2>
</br>

<c:forEach items="${RESULT}" var="movie">
	Movie name: ${movie.name}<br>
	Year of release: ${movie.year}<br>	
	Genre: ${movie.genre}<br>
	-------------------------------------
	<br>
</c:forEach>


<a href="index.jsp">Back</a>
</body>
</html>