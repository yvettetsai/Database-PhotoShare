<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasAlbumsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasTagsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewCommentDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.CommentUnderPicDao" %>

<head><title>Delete a Picture</title></head>

<!--
  1. check the user is a register user
    2. check the user owns the picture
    3. remove data from hasPictures relational table
    4. remove data from hasTags relational table
    5. remove data from commentUnderPic relational table
    6. remove data from Pictures table
-->

<body>

<h2>Delete a Picture</h2>

<% 
	boolean ownPic = false;
	boolean doneDelete = false;
  	String err = null; 
	String tempPid = request.getParameter("picture_id");
	int pictureId, aid;
	if(tempPid.indexOf('=') > 0) {
		String pidS = "";
		int i;
    	for(i = 0; i < tempPid.length(); i ++) {
			if(tempPid.charAt(i) == '?'){
				break;
			}
			pidS = pidS + "" + tempPid.charAt(i);
		}
		pictureId = Integer.parseInt(pidS);

		String tempAid = tempPid.substring(i+1);
		for(i = 0; i < tempAid.length(); i++) {
			if(tempAid.charAt(i) == '=') {
				break;
			}
		}
		aid = Integer.parseInt(tempAid.substring(i+1));
	} else {
		pictureId = Integer.parseInt(tempPid);
		HasPicturesDao hasPictureDao = new HasPicturesDao();
		aid = hasPictureDao.getAlbumId(pictureId);
	}
	int uid = -1; 
%>

<%  // request user is a register user
	if (request.getUserPrincipal() != null) 
   	{ 
   	   	NewUserDao newUserDao = new NewUserDao();
		uid = newUserDao.getID(request.getUserPrincipal().getName());
		
		// get the album_id that the picture belongs to
		HasPicturesDao hasPictureDao = new HasPicturesDao();
		aid = hasPictureDao.getAlbumId(pictureId);
		
		// get the user id that the album belongs to
		HasAlbumsDao hasAlbumsDao = new HasAlbumsDao();
		int tempUid = hasAlbumsDao.getUserId(aid);
		
		if (uid == tempUid) {
			ownPic = true;
		}else {
			err = "You do not own this picture!!!";
		}
	
		if (ownPic)
		{
			HasTagsDao hasTagsDao = new HasTagsDao();
			CommentUnderPicDao comUndPicDao = new CommentUnderPicDao();
			PictureDao pictureDao = new PictureDao();
			
			doneDelete = (hasPictureDao.deletePid(pictureId) 
				& hasTagsDao.deletePid(pictureId)
				& comUndPicDao.deletePid(pictureId)
				& pictureDao.deletePid(pictureId));
		}
	}
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% if (doneDelete) {  %>

<h2>Success!</h2>

<p>The picture has been deleted<br/>

<% } %>


<h4>Return to <a href="displayPictureInAlbum.jsp?AlbumId=<%=aid%>">Previous Page</a></h4>
<h4>Return to <a href="newalbum.jsp">Album and Picture Page</a></h4>
<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h3>
			
</body>
</html>
