<%--
  Original Author: Giorgos Zervas <cs460tf@cs.bu.edu>
	Modified by: Yvette Tsai (ytsai@bu.edu)
--%>

<%@ page import="edu.bu.cs.cs460.photoshare.Picture" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>

<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<jsp:useBean id="hasPicturesBean"
             class="edu.bu.cs.cs460.photoshare.HasPicturesBean" />
<jsp:setProperty name="hasPicturesBean" property="*"/>

<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<jsp:useBean id="newUserBean"
             class="edu.bu.cs.cs460.photoshare.NewUserBean" />
<jsp:setProperty name="newUserBean" property="*"/>

<%@ page import="edu.bu.cs.cs460.photoshare.IsFriendsDao" %>
<jsp:useBean id="isFriendsBean"
             class="edu.bu.cs.cs460.photoshare.IsFriendsBean" />
<jsp:setProperty name="isFriendsBean" property="*"/>

<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>
<jsp:useBean id="newAlbumBean"
             class="edu.bu.cs.cs460.photoshare.NewAlbumBean" />
<jsp:setProperty name="newAlbumBean" property="*"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="imageUploadBean"
             class="edu.bu.cs.cs460.photoshare.ImageUploadBean">
    <jsp:setProperty name="imageUploadBean" property="*"/>
</jsp:useBean>


<html>
<head><title>Photo sharing</title></head>

<body>
<h1>Photo Sharing</h1>

Hello <b><code><%= request.getUserPrincipal().getName()  %></code></b>, click here to
<a href="/photoshare/logout.jsp">log out</a>

<h3><a href="/photoshare/searchfriend.jsp">Search and Add Friend</a></h3>

<h3><a href="newalbum.jsp">Album and Picture</a></h3>

<h3><a href="displayAllPicture.jsp">Write Comments</a></h3>

<h3><a href="listallalbums.jsp">Tag Pictures</a></h3>

<h3><a href="topusers.jsp">Top 10 Users</a></h3>

<h3><a href="searchTag.jsp">View Photos with a Specific Tag</a></h3>

<h3><a href="populartag.jsp">View Most Popular Tag</a></h3>

<h3><a href="searchmultitags.jsp?id=1">Search with Conjunctive Tag</a></h3>

<h3><a href="youmayalsolike.jsp">You May Also Like</a></h3>

<h3><a href="searchmultitags.jsp?id=2">Tag Recommendation</a></h3>

</body>
</html>
