<%@ page import="edu.bu.cs.cs460.photoshare.Picture" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>

<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>

<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<jsp:useBean id="hasPicturesBean"
             class="edu.bu.cs.cs460.photoshare.HasPicturesBean" />
<jsp:setProperty name="hasPicturesBean" property="*"/>

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
<head><title>Upload Picture</title></head>

<body>

<% 
   boolean uploadSuccess = false;
   String err = null; 
%>

<%  
  String albumName = request.getParameter("upLoadPicAlbumName");
	
	if (albumName != null && !albumName.equals("")) 
   	{ 
		NewUserDao newUserDao = new NewUserDao();
	   	int userId = newUserDao.getID(request.getUserPrincipal().getName());
	   	// the album name is invalid 
		 NewAlbumDao newAlbumDao = new NewAlbumDao();
	  	if(!newAlbumDao.validAlbum(albumName, userId)){
	   		err = "This album doesn't exist or not belongs to you";
		
		// the album name is valid
		}else{	
			PictureDao pictureDao = new PictureDao();
    		try {
        		Picture picture = imageUploadBean.upload(request);
       			if (picture != null) {
            		pictureDao.save(picture);
					int pid = pictureDao.getPicId(picture);
					if(pid <= 0){
						err = "invalid pid";
					}else{
					int aid = newAlbumDao.getAlbumIdSpecificUser(userId, albumName);
					HasPicturesDao hasPicturesDao = new HasPicturesDao();
					uploadSuccess = hasPicturesDao.create(pid, aid);
					}
        		}
    		} catch (FileUploadException e) {
        		e.printStackTrace();
    		} finally {
			}	
		}
	}
%>


<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<%  if (!uploadSuccess) { %>

<h4>Choose a picture to upload</h4>

<form action="uploadpicture.jsp?upLoadPicAlbumName=<%=request.getParameter("upLoadPicAlbumName")%>"
	enctype="multipart/form-data" method="post">
    Filename: <input type="file" name="filename"/>
    <input type="submit" value="Upload"/><br/>
</form>

<% 	}else {%>

<h4> Success! You picture has been uploaded</h4>

<% } %>

Return to <a href="index.jsp">home page</a>

</body>
</html>
