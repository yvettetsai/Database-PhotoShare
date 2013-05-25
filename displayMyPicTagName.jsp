<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewTagDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasTagsDao" %>

<html>
<head><title>List All Pictures With a Specific Tag Name</title></head>

<!-- 
  1. get thd TID that the user request
    2. get all the PIDs that associate with the TID
    3. display only the PIDs that the request user owns
    4. if the user is not registered, display all pictures directly
    5. if the user is registered, the user can switch betwe
-->

<body> 


<%  
	NewTagDao newTagsDao = new NewTagDao();
	int tid = Integer.parseInt(request.getParameter("tag_Id"));
	String tagName = newTagsDao.getTagName(tid);
	HasTagsDao hasTagsDao = new HasTagsDao();
%>

<h2>All Your Pictures With Tag Name "<%=tagName%>" </h2>
<table>
<%
	// obtain a list of pid tag with tid
	List<Integer> pictureIds = hasTagsDao.allPicId(tid);
	NewUserDao newUserDao = new NewUserDao();
	HasPicturesDao hasPicturesDao = new HasPicturesDao();
	NewAlbumDao newAlbumDao = new NewAlbumDao();
	int tempUId = newUserDao.getID(request.getUserPrincipal().getName());
	for (Integer pictureId : pictureIds) {
		int tempAId = hasPicturesDao.getAlbumId(pictureId);
		if (newAlbumDao.validAlbum(tempAId,tempUId)) {
%>
		<tr>
       		<td><a href="/photoshare/displayPicture.jsp?picture_id=<%=pictureId%>">
            		<img src="/photoshare/img?t=1&picture_id=<%=pictureId%>"/></a>
        	</td>
       	</tr>	
<% 	
		}	
	}
%>
</table>

<h4>Display <a href="displayPicTagName.jsp?tag_Id=<%=tid%>">All Pictures</a> with Tag Name "<%=tagName%>" </h4>
<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>
