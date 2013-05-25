<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<jsp:useBean id="newUserBean"
             class="edu.bu.cs.cs460.photoshare.NewUserBean" />
<jsp:setProperty name="newUserBean" property="*"/>

<%@ page import="edu.bu.cs.cs460.photoshare.IsFriendsDao" %>
<jsp:useBean id="isFriendsBean"
             class="edu.bu.cs.cs460.photoshare.IsFriendsBean" />
<jsp:setProperty name="isFriendsBean" property="*"/>

<html>
<head><title>List All Friends</title></head>

<body>
<!-- We want to show the form unless we successfully create a new user -->
<h2>List of your Friends </h2>

<table>
    <tr>
        <%
  		NewUserDao newUserDao = new NewUserDao();
			IsFriendsDao newIsFriendsDao = new IsFriendsDao();
			int myID = newUserDao.getID(request.getUserPrincipal().getName());
            List<Integer> myFriendIds = newIsFriendsDao.allFriends(myID);
            for (Integer myFriendId : myFriendIds) {
				String temp = newUserDao.getUsersName(myFriendId);
        %>
       		My Friend:<a href="listuseralbum.jsp?user_id=<%=myFriendId%>"> <%= temp %></a><br/>
        <%
			}
        %>
    </tr>
</table>

<h4>Return to<a href="index.jsp">Home Page</a></h3><br/>
<h4>Return to<a href="main.jsp">Main Page</a></h3><br/>

</body>
</html>
