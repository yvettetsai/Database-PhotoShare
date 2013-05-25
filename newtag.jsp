<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.lang.util.*" %>
<%@ page import="java.lang.String" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewTagDao" %>
<jsp:useBean id="newTagBean"
             class="edu.bu.cs.cs460.photoshare.NewTagBean" />
<jsp:setProperty name="newTagBean" property="*"/>

<%@ page import="edu.bu.cs.cs460.photoshare.CommentUnderPicDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasAlbumsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasTagsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>

<html>
<head><title>Tag Picture</title></head>

<body>

<!--
  1. first check the request user does own the picture
    2. check the enter tag name is valid
    		if exist in the system, get the existing tag id
            else create a new tag 
    3. insert into Tags table
    4. insert into hasTags relational table
--> 

<% boolean ownPic = true;
   boolean insertHasTag = false;
   String err = null; 
   String tempPId = request.getParameter("picture_id");
   int pictureId = Integer.parseInt(tempPId); 
%>

<% // the inpiut tag name is not empty 
	if (!newTagBean.getName().equals("")) { 

   	   	NewUserDao newUserDao = new NewUserDao();
		int uid = newUserDao.getID(request.getUserPrincipal().getName());
   	   	
		// get the album_id that the picture belongs to
		HasPicturesDao hasPictureDao = new HasPicturesDao();
		int aid = hasPictureDao.getAlbumId(pictureId);
		
		// get the user_id that owns the above album_id 
		HasAlbumsDao hasAlbumsDao = new HasAlbumsDao();
		int tempUid = hasAlbumsDao.getUserId(aid);
			
		// check wheather the request user own such photo
		if (uid != tempUid) {
			ownPic = false;
			err = "You can't make tag on other's picture";
		}
	   
	   // now we are sure the request user does own the picture 
	   if (ownPic) {
		   
		   // verify whether the inputTagName contains space
		   String inputTagName = newTagBean.getName();
		   int len = inputTagName.length();
		   
		   boolean containSpace = false;
		   for(int i = 0; i < len; i ++)	{
		   		if (inputTagName.charAt(i) == ' ') {
					containSpace = true;
				}
			}
		   
		   // if input tag name contain no space
		   if(!containSpace) {
		   		NewTagDao newTagDao = new NewTagDao();
		   		HasTagsDao hasTagsDao = new HasTagsDao();
		   
		  		// check whether input tag name already exist in the data
		   		int tempTId = newTagDao.getTagId(inputTagName);
				
				// if not exist, create a new tag with the input tag name
				if (tempTId <= 0) {
					newTagDao.create(inputTagName);
				}
		   
		   		int tid = newTagDao.getTagId(inputTagName);
		   		insertHasTag = hasTagsDao.create(pictureId, tid);
		   }else {
			   err = "Tag name cannot contain space!";
		   }
	   }
} 
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% if (!insertHasTag) { %>

<h4>Please enter tag name (single word only)</h4>

<form action="newtag.jsp?picture_id=<%=pictureId%>" method="post">
  Tag Name: <input type="text" name="tagName"/>
  <input type="submit" value="Tag"/><br/>
</form>


<% }
   else { %>

<h2>Success!!!</h2>


<% } %>

<h4>Return to <a href="newalbum.jsp">Album and Picture Page</a></h4>
<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>
