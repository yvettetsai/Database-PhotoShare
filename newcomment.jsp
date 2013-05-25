<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewCommentDao" %>
<jsp:useBean id="newCommentBean"
             class="edu.bu.cs.cs460.photoshare.NewCommentBean" />
<jsp:setProperty name="newCommentBean" property="*"/>

<%@ page import="edu.bu.cs.cs460.photoshare.CommentUnderPicDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasAlbumsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>

<html>
<head><title>Write Comment</title></head>

<body>

<!--
  1. first check the request user doesn't own the picture
    2. insert into Comments table
    3. insert into hasComments relational table
--> 

<% boolean ownPic = false;
   boolean insertCom = false;
   boolean insertComUndPic = false;
   String err = null; 
   String tempUId = request.getParameter("picture_id");
   int pictureId = Integer.parseInt(tempUId); %>

<% if (!newCommentBean.getText().equals("")) { 
   	   int uid = -1;
   	   NewUserDao newUserDao = new NewUserDao();
	   
	   // the user is a registered user
	   if (request.getUserPrincipal() != null) {
   	   		uid = newUserDao.getID(request.getUserPrincipal().getName());
			// get the album_id that the picture belongs to
			HasPicturesDao hasPictureDao = new HasPicturesDao();
			int aid = hasPictureDao.getAlbumId(pictureId);
			HasAlbumsDao hasAlbumsDao = new HasAlbumsDao();
			int tempUid = hasAlbumsDao.getUserId(aid);
	
			if (uid == tempUid) {
				ownPic = true;
				err = "You can't comment on your own picture!!!";
			}
			
	   }
	   
	   // now we are sure the request user doesn't own the picture 
	   if (!ownPic) {
		   NewCommentDao newCommentDao = new NewCommentDao();
		   CommentUnderPicDao comUnderPicDao = new CommentUnderPicDao();
		   String inputText = newCommentBean.getText();
		   insertCom = newCommentDao.create(inputText, uid);
		   int cid = newCommentDao.getComId(inputText, uid);
		   insertComUndPic = comUnderPicDao.create(pictureId, cid);
	   }
} 
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% if (!insertCom && !insertComUndPic) { %>

<h4>Please enter your comment</h4>

<form action="newcomment.jsp?picture_id=<%=tempUId%>" method="post">
  Comment: <input type="text" name="text"/>
  <input type="submit" value="Submit"/><br/>
</form>


<% }
   else { %>

<h2>Success!!!</h2>


<% } %>

<% if (request.getUserPrincipal() != null) { %>
	<h4>Return to <a href="newalbum.jsp">Album and Picture Page</a></h4>
<% } %>
<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>
