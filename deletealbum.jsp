<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>

<%@ page import="edu.bu.cs.cs460.photoshare.HasAlbumsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasAlbumsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasTagsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewCommentDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.CommentUnderPicDao" %>


<head><title>Delete an Album</title></head>

<body>

<h2>Delete an Album </h2>

<%   boolean ownAlb = false;
	boolean doneDelete = false;
  	String err = null; 
	int uid = -1; 
	int aid = -1;

	String delAlbumName = request.getParameter("deleteAlbumName");
	NewAlbumDao newAlbumDao = new NewAlbumDao();
	
	if ((delAlbumName != null) && (!delAlbumName.equals("")) 
			&& (newAlbumDao.albumNameExist(delAlbumName) > 0)) 
   	{ 
	   NewUserDao newUserDao = new NewUserDao();
	   uid = newUserDao.getID(request.getUserPrincipal().getName());
	   
	   if(newAlbumDao.validAlbum(delAlbumName, uid)) {
		   ownAlb = true;
	   }else {
		   err = "This album doesn't exist or doesn't belong to you";
	   }
	   
	   if(ownAlb) 
	   {
		   	HasPicturesDao hasPictureDao = new HasPicturesDao();
		   	HasTagsDao hasTagsDao = new HasTagsDao();
			CommentUnderPicDao comUndPicDao = new CommentUnderPicDao();
			PictureDao pictureDao = new PictureDao();
			HasAlbumsDao hasAlbumsDao = new HasAlbumsDao();
		   	
			aid = newAlbumDao.getAlbumId(delAlbumName, uid);
		   	List<Integer> pictureIds = hasPictureDao.allPicIds(aid);
			for(Integer pictureId: pictureIds) {
				hasPictureDao.deletePid(pictureId);
				hasTagsDao.deletePid(pictureId);
				comUndPicDao.deletePid(pictureId);
				pictureDao.deletePid(pictureId);
			}
			if(hasAlbumsDao.deleteAid(aid, uid)){doneDelete = true;}
			if(newAlbumDao.deleteAid(aid)){doneDelete = true;}    
	   }
		
	} else {
		err = "Invalid album name";
	}
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% 
	if (doneDelete) {  %>

<h2>Success!</h2>

<p>Album "<%=delAlbumName%>" has been deleted.<br/>

<% }
	if((!doneDelete) && (err == null)) { %>

<h2>Failed...</h2>

<p>Please try again!</p>

<% } %>

<h4>Return to <a href="newalbum.jsp">Album and Picture</a>page.</h4>
<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>
			
</body>
</html>
