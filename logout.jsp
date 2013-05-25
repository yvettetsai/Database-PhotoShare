<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Logout</title></head>

<body>
<h2>Logged out</h2>

<% session.invalidate(); %>
<a href="main.jsp">Return to Main Page</a>
</body>
</html>
