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
<head><title>View Pictures With Specific Tag</title></head>

<body>

<% // the inpiut tag name is not empty 
  boolean tagFound = false;
	int tid = -1;
	String err = null;
	if (!newTagBean.getName().equals("")) { 
		  
		   String inputTagName = newTagBean.getName();
		   int len = inputTagName.length();
		   boolean containSpace = false;
		   for(int i = 0; i < len; i ++){
		   		if (inputTagName.charAt(i) == ' ') {
					containSpace = true;
				}
			}

		   if(!containSpace) {
		   		NewTagDao newTagDao = new NewTagDao();
		   		HasTagsDao hasTagsDao = new HasTagsDao();
		   
		   		int tempTId = newTagDao.getTagId(inputTagName);
				
				if (tempTId <= 0) {
					err = "Such tag does not exist";
				}else {
					tagFound = true;
					tid = newTagDao.getTagId(inputTagName);
				}
		   }else {
			   err = "Tag name cannot contain space!";
		   }
	} 
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% if (!tagFound) { %>

<h4>Please enter tag name (single word only)</h4>

<form action="searchTag.jsp" method="post">
  Tag Name: <input type="text" name="tagName"/><br>
  <input type="submit" value="Search"/><br/>
</form>


<% } else { 
		if(request.getUserPrincipal() != null) {
%>

			<h2>Tag Name Exist</h2>

			<h3><a href="displayMyPicTagName.jsp?tag_Id=<%=tid%>"> My Pictures</a></h3>
<% 		}else {  %>

			<h2>Tag Name Exist</h2>

			<h3><a href="displayPicTagName.jsp?tag_Id=<%=tid%>"> All Pictures</a></h3>

<%		}
	}
%>

<h4>Return to <a href="main.jsp">Main Page</a></h3>

</body>
</html>
