<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Result of query</title>
</head>
<body>


<br>
<h2>Showing content from database on selection of: ${media} with sorting on: ${genre}</h2>
<br>

<c:forEach items="${RESULT}" var="movie">
	Movie name: ${movie.id}<br>
	Year of release: ${movie.title}<br>	
	Genre: ${ genre }
	-------------------------------------
	<br>
</c:forEach>


<a href="index.jsp">Back</a>
</body>
</html>