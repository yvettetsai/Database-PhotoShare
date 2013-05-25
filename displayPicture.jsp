<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.lang.String" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasPicturesDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewTagDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasTagsDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewCommentDao" %>
<jsp:useBean id="newCommentBean"
             class="edu.bu.cs.cs460.photoshare.NewCommentBean" />
<jsp:setProperty name="newCommentBean" property="*"/>

<%@ page import="edu.bu.cs.cs460.photoshare.CommentUnderPicDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.HasAlbumsDao" %>


<html>
<head><title>Display Signal Picture</title></head>

<body>

<h2>Picture</h2>

<%   String tempPid = request.getParameter("picture_id");
	int pid, aid;
	if (tempPid.indexOf('=') > 0) {
		String pidS = "";
		int i;
    	for(i = 0; i < tempPid.length(); i ++) {
			if(tempPid.charAt(i) == '?'){
				break;
			}
			pidS = pidS + "" + tempPid.charAt(i);
		}
		pid = Integer.parseInt(pidS);

		String tempAid = tempPid.substring(i+1);
		for(i = 0; i < tempAid.length(); i++) {
			if(tempAid.charAt(i) == '=') {
				break;
			}
		}
		aid = Integer.parseInt(tempAid.substring(i+1));
	}else {
		pid = Integer.parseInt(tempPid);
		HasPicturesDao hasPictureDao = new HasPicturesDao();
		aid = hasPictureDao.getAlbumId(pid);
	}
%>
    
<img src="/photoshare/img?t=1&picture_id=<%=pid%>"/>

<h4>I want to <a href="newcomment.jsp?picture_id=<%=pid%>">comment</a></h4>
<% 
	if(request.getUserPrincipal() != null) { 
%>
		<h4>I want to <a href="newtag.jsp?picture_id=<%=pid%>">tag</a> picture</h4>
		<h4>I want to <a href="deletepicture.jsp?picture_id=<%=pid%>?album_id=<%=aid%>">delete</a> this picture</h4>
<%	} %>

<!-- add code to display all the comment under this photo -->

<h2>Comments On This Picture</h2>

<table>
        <% 
			NewUserDao newUserDao = new NewUserDao();
			NewCommentDao newComDao = new NewCommentDao();
			CommentUnderPicDao comUndPicDao = new CommentUnderPicDao();
            List<Integer> comIds = comUndPicDao.allComsId(pid);
			
            for (Integer comId : comIds) {
				String tempText = newComDao.getText(comId);
				int tempOwnerID = newComDao.getOwnerId(comId);
				String tempEmail = "";
				if (tempOwnerID >= 0) {
					tempEmail = newUserDao.getUsersName(tempOwnerID);
				} else {
					tempEmail = "Non-Register User";
				}
		%>
        	<tr>
       			<td><p><%=tempText%></p></td>
            </tr>
            <tr><prep>
                <td><p>Commented By: <%=tempEmail%></p></td>
			</tr></pre>
        <%
            }
        %>
</table>

<h2>Tags on This Picture</h2>

<!-- display all the existing tag belongs to this picture -->

<table>
        <% 
			NewTagDao newTagDao = new NewTagDao();
			HasTagsDao hasTagsDao = new HasTagsDao();
            List<Integer> tagIds = hasTagsDao.allTId(pid);
            for (Integer tagId : tagIds) {
				String tagName = newTagDao.getTagName(tagId); 
		%>
        		<tr>
       		 		<td><a href="displayPicTagName.jsp?tag_Id=<%=tagId%>"><%=tagName%></a></td>
                </tr>
       	<%		
            }
        %>
</table>

<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>
