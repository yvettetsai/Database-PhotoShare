<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>

<html>
<head><title>List All Pictures</title></head>

<body>
<h2>Existing pictures</h2>
<table>
        <%
  		PictureDao pictureDao = new PictureDao();
            List<Integer> pictureIds = pictureDao.allPicturesIds();
			HasPicturesDao hasPicturesDao = new HasPicturesDao();
            for (Integer pictureId : pictureIds) {
				int tempAId = hasPicturesDao.getAlbumId(pictureId);
		%>
        		<tr>
       		 		<td><a href="/photoshare/displayPicture.jsp?picture_id=<%=pictureId%>?album_id=
						<%=tempAId%>"><img src="/photoshare/img?t=1&picture_id=<%=pictureId%>"/></a>
        			</td>
                </tr>
       	<%
            }
        %>
</table>

<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>
