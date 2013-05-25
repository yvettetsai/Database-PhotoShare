<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<jsp:useBean id="newUserBean"
             class="edu.bu.cs.cs460.photoshare.NewUserBean" />
<jsp:setProperty name="newUserBean" property="*"/>

<%@ page import="edu.bu.cs.cs460.photoshare.IsFriendsDao" %>
<jsp:useBean id="isFriendsBean"
             class="edu.bu.cs.cs460.photoshare.IsFriendsBean" />
<jsp:setProperty name="isFriendsBean" property="*"/>

<html>
<head><title>Search and Add Friends</title></head>

<body>

<% boolean notFound = true;
   String err = null; %>

<% if (!newUserBean.getEmail().equals("")) 
   { 
       NewUserDao newUserDao = new NewUserDao();
     String addFriendEmail = newUserBean.getEmail();
	   if(newUserDao.validUser(addFriendEmail))
	   {
       		IsFriendsDao isFriendsDao = new IsFriendsDao();	
			int addFriendUId = newUserDao.getID(addFriendEmail);
			int requestUId = newUserDao.getID(request.getUserPrincipal().getName());
			if(addFriendUId != requestUId){
				notFound = false;
%>

<script type="text/javascript">
	function addFriend()
	{
		<%	isFriendsDao.create(requestUId, addFriendUId); %>
		alert("Successfully add your friend!");
	}
</script>

<%			}else{
				err = "You can't be friend with yourself";
			}
    	}
		else{
			err = "Your friends is not a registered user";
		}
   }
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% if (notFound) { %>

<h2>Search and Add your friend</h2>
<h4>Please enter your friend's email</h4>

<form action="searchfriend.jsp" method="post">
  My Friend's Email: <input type="text" name="email"/><br>
  <input type="submit" value="Search"/><br/>
</form>


<% }
   else { %>

<h2>You Found it!</h2>

<p>You friend with email <%= newUserBean.getEmail() %> is a user in Photo Share. <br>
<form>
<input type="button" value="Add my friend now" onClick="addFriend()" />
</form>

Return to <a href="index.jsp">Home Page</a><br/>


<% } %>

<form action="/photoshare/listallfriends.jsp" target="_blank"> 
	<input type="submit" value="List Friends">
</form>

<h4><a href="main.jsp">Return to Main Page</a></h3><br/>

</body>
</html>
