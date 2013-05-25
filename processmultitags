<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.String" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewTagDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.CommentUnderPicDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasAlbumsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasTagsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>

<html>
<head><title>Process Conjunctive Tag Search</title></head>

<body>

<h2> Here are all the pictures that contains all the tags </h2>

<% 
  String err = null;
	boolean process = false;
	String inputTags = request.getParameter("tagString");
	String tempTags = request.getParameter("tagString");
	List<String> tagNames = new ArrayList<String>();
	
	while(tempTags.indexOf(' ') != -1) {
		int i = tempTags.indexOf(' ');
		tagNames.add(tempTags.substring(0, i));
		tempTags = tempTags.substring(i+1);
	}
	tagNames.add(tempTags);
	
	
	NewTagDao newTagDao = new NewTagDao();	
		
	// test through all the tag name see if all are valid		
	for(int i = 0; i < tagNames.size(); i++) {
		if (newTagDao.getTagId(tagNames.get(i)) <= 0) {
			err = "Invalid input tag name";
			break;
		}
	}
	
	HasPicturesDao hasPicturesDao = new HasPicturesDao();
	HasTagsDao hasTagsDao = new HasTagsDao();
	int tid1 = newTagDao.getTagId(tagNames.get(0));
	List<Integer> pids = hasTagsDao.allPicId(tid1);
	
	for (int i = 1; i < tagNames.size(); i ++) {
		int tempTid = newTagDao.getTagId(tagNames.get(i));
		for (int j = 0; j < pids.size(); j ++) {
			if (!hasTagsDao.pidExistInTid((int)(pids.get(j)),tempTid)) {
				pids.remove(j);
				j --;
			}
		}
	}
	
	process = true;
%>


<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<table>
<% 
	if (process) {  
		for (Integer pid : pids) {
			int aid = hasPicturesDao.getAlbumId(pid);
%>
        		<tr>
       		 		<td><a href="/photoshare/displayPicture.jsp?picture_id=<%=pid%>?album_id=<%=aid%>">
            		<img src="/photoshare/img?t=1&picture_id=<%=pid%>"/></a>
        			</td>
                </tr>
<%	
		}
	}
%>
</table>

<% 
	if((!process) && (err == null)) { %>

		<h2>Failed...</h2>

		<p>Please try again!</p>

<% } %>


<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>
