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
<!-- We want to show the form unless we successfully create a new user -->
<% boolean showForm = true;
   String err = null; %>

<%   
	String albName = request.getParameter("NewAlbumName");
	
	if (albName != null && !albName.equals("")) {
	
       NewUserDao newUserDao1 = new NewUserDao();
	   int userID = newUserDao1.getID(request.getUserPrincipal().getName());
	   
	   NewAlbumDao newAlbumDao1 = new NewAlbumDao();
       boolean success1 = newAlbumDao1.create(albName, userID);
	   
	   HasAlbumsDao hasAlbumsDao1 = new HasAlbumsDao();
	   boolean success2 = hasAlbumsDao1.create(userID, newAlbumDao1.getAlbumId(userID));
	   
       if (success1 && success2) {
         showForm = false;
       } else {
         err = "Couldn't create album";
       }
   }
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% if (!showForm) {  %>

<h2>Success!</h2>

<p>A new album has been created with name <%= request.getParameter("NewAlbumName") %>.<br/>

<% } %>


</body>
</html>
