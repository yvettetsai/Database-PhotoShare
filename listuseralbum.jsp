<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.lang.String" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>

<html>
<head><title>List User's All Albums</title></head>

<%   
	String user = request.getParameter("user_id");
   	int uid = Integer.parseInt(user);
  	NewUserDao newUserDao = new NewUserDao();
	NewAlbumDao albumsDao = new NewAlbumDao();
	String email = newUserDao.getUsersName(uid);
%>


<body
<h2>List of User: <%=email%> Albums </h2>

<table>
    <tr>
        <%	
            List<Integer> myAlbumIds = albumsDao.allMyAlbumsId(uid);
            for (Integer myAlbumId : myAlbumIds) {
				String temp = albumsDao.getAlbumName(myAlbumId);
        %>
               	<a href="displayPictureInAlbum.jsp?AlbumId=<%=myAlbumId%>"> <%=temp%></a><br/>
        <%} %>
    </tr>
</table>
        
<h4>Return to<a href="index.jsp">Home Page</a></h4>
<h4>Return to<a href="main.jsp">Main Page</a></h4>
        
</body>
</html>
