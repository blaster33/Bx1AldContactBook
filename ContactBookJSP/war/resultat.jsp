<html>
	<head>
	<title>Admin Panel </title>
	</head>
	<body>
	<table width = 100%>
	<tr width = 100%>
	<td width = 100% align="right">
	<form method="POST" action="ContactBookController">
	<label for="logout">Welcome <%= request.getAttribute("username") %></label>
	<input type="hidden" name="do" value="logout"/>
	<input type="submit" value="Logout" name="logout"/>
	</form>
	</td>
	</tr>
	</table>
	<jsp:useBean id="beanList" class="java.util.ArrayList" scope="request" />
	<jsp:useBean id="userBean" class="bean.UserBean" scope="request" />

		<% for (int i = 0; i < beanList.size(); i++) {
			userBean = (bean.UserBean) beanList.get(i);%>
		<br /> <%= userBean.getLoginName() %>
		<% } %>
		
	</body>
</html>