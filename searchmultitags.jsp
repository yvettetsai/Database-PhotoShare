<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.String" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewTagDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.CommentUnderPicDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasAlbumsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasTagsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>

<html>
<head><title>Obtain the Conjunctive Tag Input String</title></head>

<body>

<%
  int choice = Integer.parseInt(request.getParameter("id"));
	
	if(choice == 1) {
%>
	<h4>Please enter one or more tag name separate with space</h4>
	<form action="processmultitags.jsp?tagString=" method="get" target="_blank">
  		Conjuntive Tag: <input type="text" name="tagString"/>
  		<input type="submit" value="Search"/><br/>
	</form>
<%
	} else {
%>
	<h4>Please enter one or more tag name separate with space</h4>
    <p>(enter some tag name you have in mind)</p>
    
	<form action="tagrecomand.jsp?tagString=" method="get" target="_blank">
  		Tags: <input type="text" name="tagString"/>
  		<input type="submit" value="Search"/><br/>
	</form>
    
<%
	}
%>


<h4>Return to <a href="main.jsp">Main Page</a></h3>

</body>
</html>

