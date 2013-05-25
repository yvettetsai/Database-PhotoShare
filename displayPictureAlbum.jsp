<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>

<html>
<head><title>List All Pictures In An Album</title></head>

<body>
<!-- We want to show the form unless we successfully create a new user -->

<h2>Existing pictures In This Album</h2>
<table>
        <%
  		if(request.getUserPrincipal() != null) {
				NewUserDao newUserDao = new NewUserDao();
				PictureDao pictureDao = new PictureDao();
				NewAlbumDao newAlbumDao = new NewAlbumDao();
				HasPicturesDao hasPicturesDao = new HasPicturesDao();
           	 	List<Integer> pictureIds = pictureDao.allPicturesIds();
            	for (Integer pictureId : pictureIds) {
					int tempAId = hasPicturesDao.getAlbumId(pictureId);
					int tempAId2 = Integer.parseInt(request.getParameter("AlbumId"));
					int tempUId = newUserDao.getID(request.getUserPrincipal().getName());
					if (tempAId == tempAId2 && newAlbumDao.validAlbum(tempAId,tempUId) ) { 
		%>
        			<tr>
       		 			<td><a href="/photoshare/displayPicture.jsp?picture_id=<%=pictureId%>?album_id=<%=tempAId%>">
            			<img src="/photoshare/img?t=1&picture_id=<%=pictureId%>"/></a>
        				</td>
                	<tr>	
       	<%			}
				}
			} else {
				PictureDao pictureDao = new PictureDao();
				NewAlbumDao newAlbumDao = new NewAlbumDao();
				HasPicturesDao hasPicturesDao = new HasPicturesDao();
            	List<Integer> pictureIds = pictureDao.allPicturesIds();
            	for (Integer pictureId : pictureIds) {
					int tempAId = hasPicturesDao.getAlbumId(pictureId);
					int tempAId2 = Integer.parseInt(request.getParameter("AlbumId"));
					if (tempAId == tempAId2) { 
        %>
        			<tr>
       		 			<td><a href="/photoshare/displayPicture.jsp?picture_id=<%=pictureId%>?album_id=<%=tempAId%>">
            			<img src="/photoshare/img?t=1&picture_id=<%=pictureId%>"/></a>
        				</td>
               		<tr>
       	<%			}
				}
			}
		%>
</table>

<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>
