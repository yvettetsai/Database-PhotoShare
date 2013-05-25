<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewTagDao" %>


<html>
<head><title>List All Tag Name</title></head>

<body>

<h2>List of existing Tags</h2>
<table>
        <%
  		NewTagDao newTagDao = new NewTagDao();
            List<Integer> tagIds = newTagDao.allTagIds();
            for (Integer tagId : tagIds) {
				String tagName = newTagDao.getTagName(tagId);
		%>
        		<tr>
       		 		<td><a href="displayMyPicTagName.jsp?tag_Id=<%=tagId%>"><%=tagName%></a></td>
                <tr>
       	<%		
            }
        %>
</table>

<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>

