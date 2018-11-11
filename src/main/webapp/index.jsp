<html>
<body>


<div align="center">
<h2>Welcome to Movie and series demo</h2> <br/>
Make your selections and enjoy that sweet sql in action
</div>
<br/>
<br/>
<form action="TmdbServlet" method="post">
<div align="left" id="movieOrSeries">
	<select name="media">
		<option value="movie">Movies</option>
	</select>
</div>
<br/>
<div align=left id="sorting">
	<select name="genre">
		<option value="thriller">Thriller</option>
		<option value="comedy">Comedy</option>
		<option value="drama">Drama</option>
		<option value="sci-fi">Sci-fi</option>
	</select>
</div>
<br/>
<br/>
<button type="submit">Search</button>

</form>



</body>
</html>
