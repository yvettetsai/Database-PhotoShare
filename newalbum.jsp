<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<jsp:useBean id="newUserBean"
             class="edu.bu.cs.cs460.photoshare.NewUserBean" />
<jsp:setProperty name="newUserBean" property="*"/>

<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>
<jsp:useBean id="newAlbumBean"
             class="edu.bu.cs.cs460.photoshare.NewAlbumBean" />
<jsp:setProperty name="newAlbumBean" property="*"/>

<%@ page import="edu.bu.cs.cs460.photoshare.HasAlbumsDao" %>
<jsp:useBean id="hasAlbumsBean"
             class="edu.bu.cs.cs460.photoshare.HasAlbumsBean" />
<jsp:setProperty name="hasAlbumsBean" property="*"/>

<html>
<head><title>Album and Pictures</title></head>

<body>

<h2>Create a New Album </h2>

<form action="/photoshare/createnewalbum.jsp?NewAlbumName=" target="_blank" method="get">
  Album Name: <input type="text" name="NewAlbumName"/>
  <input type="submit" value="Create"/><br/>
</form>

<h2>Delete an Album</h2>

<form action="/photoshare/deletealbum.jsp?deleteAlbumName=" target="_blank" method="get">
 Album Name: <input type="text" name="deleteAlbumName"/>
         <input type="submit" value="Delete"/><br/>
</form>

<h2>Upload Picture to an Album</a></h2>

<form action="/photoshare/uploadpicture.jsp?upLoadPicAlbumName=" target="_blank" method="get">
 Album Name: <input type="text" name="upLoadPicAlbumName"/>
       	<input type="submit" value="Go"/><br/>
</form>

<form action="/photoshare/listallalbums.jsp" target="_blank"> 
	<input type="submit" value="List of Albums">
</form>



<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>
