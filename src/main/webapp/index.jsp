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
		<option value="series">Series</option>
		<option value="both">Both</option>
	</select>
</div>
<br/>
<div align=left id="sorting">
	<select name="sort">
		<option value="top_rated">Top rated</option>
		<option value="popular">Popular</option>
		<option value="upcoming">Upcoming</option>
	</select>
</div>
<br/>
<br/>
<button type="submit">Search</button>

</form>



</body>
</html>
