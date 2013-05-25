<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>

<html>
<head><title>List All Albums</title></head>

<body>
<h2>List of your Albums </h2>

<table>
    <tr>
        <%  NewUserDao newUserDao = new NewUserDao();
			NewAlbumDao albumsDao = new NewAlbumDao();
			int myID = newUserDao.getID(request.getUserPrincipal().getName());
            List<Integer> myAlbumIds = albumsDao.allMyAlbumsId(myID);
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
