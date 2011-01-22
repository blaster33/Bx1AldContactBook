<html>
	<head>
	<title>Admin Panel </title>
	</head>
	<body>
		<h1>Admin Panel Login</h1>
		<% String error = (String)request.getAttribute("errorMessage");
			if ( error != null ) {%>
			<SPAN style="color: red"> <%= error %> </SPAN>
			<% } %>
		<form method="POST" action="ContactBookController">
			<label for="login">Enter your username </label>
			<input type="text" name="username"/><br><br>
			<label for="password">Enter your password </label>
			<input type="password" name="password"/><br><br>
			<input type="hidden" name="do" value="login"/>
			<input type="submit" value="Submit"/>
			<input type="reset" value="Reset"/>
		</form>
	</body>
</html>