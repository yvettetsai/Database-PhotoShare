<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<head><title>Popularity List of Tag</title></head>

<body>

<h2>List of Tag from Most Popular to Least Popular</h2>
<table>
        <% 
  		NewTagDao newTagDao = new NewTagDao();
			HasTagsDao hasTagsDao = new HasTagsDao();
            List<Integer> tagIds = hasTagsDao.rankTId();
			if(tagIds.size() > 0) {
            	for (Integer tagId : tagIds) {
					String tagName = newTagDao.getTagName(tagId); 
		%>
        		<tr>
       		 		<td><a href="displayPicTagName.jsp?tag_Id=<%=tagId%>"><%=tagName%></a></td>
                </tr>
       	<%		
            	}
			}
        %>
</table>

<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>
