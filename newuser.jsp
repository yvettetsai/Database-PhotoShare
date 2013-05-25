<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<jsp:useBean id="newUserBean"
             class="edu.bu.cs.cs460.photoshare.NewUserBean" />
<jsp:setProperty name="newUserBean" property="*"/>

<html>
<head><title>New User</title></head>

<body>
<!-- We want to show the form unless we successfully create a new user -->
<% boolean showForm = true;
   String err = null; %>

<% if (request.getUserPrincipal() == null) {
   if (!newUserBean.getEmail().equals("")) {
    	if (!newUserBean.getPassword1().equals(newUserBean.getPassword2())) {
       		err = "Both password strings must match";
     	}
     	else if (newUserBean.getPassword1().length() < 4) {
       		err = "Your password must be at least four characters long";
     	}
	 	else if (newUserBean.getFirstName().length() < 1) {
	 		err = "You must enter your first name";
	 	}
	 	else if (newUserBean.getLastName().length() < 1) {
	 			err = "You must enter your last name";
	 	}
	 	else if (newUserBean.getDateOfBirth().length() < 1) {
	 		err = "You must enter your date of birth";
	 	} 
     	else {
       	// We have valid inputs, try to create the user
       		NewUserDao newUserDao = new NewUserDao();
       		boolean success = newUserDao.create(newUserBean.getEmail(),
             	newUserBean.getPassword1(), newUserBean.getFirstName(),
			  	newUserBean.getLastName(), newUserBean.getDateOfBirth(), 
			  	newUserBean.getGender(), newUserBean.getEducation(),
			  	newUserBean.getHCity(), newUserBean.getHState(), 
			  	newUserBean.getHCountry(), newUserBean.getCCity(),
			  	newUserBean.getCState(), newUserBean.getCCountry());
       		if (success) {
         		showForm = false;
       		} else {
         		err = "Couldn't create user (that email may already be in use)";
       		}
     	}
   	}
 } else {
 	err = "You are a registered user already!";
 }
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% if (showForm) { %>

<h2>New User Information</h2></br>
<h4>Please enter the following information to register</h4>

<form action="newuser.jsp" method="post">
  Email: <input type="text" name="email"/><br>
  Password: <input type="password" name="password1"/><br>
  Re-enter password: <input type="password" name="password2"/><br>
  First Name: <input type="text" name="firstName"/><br>
  Last Name: <input type="text" name="lastName"/><br>
  Date of Birth (YYYY-MM-DD): <input type="text" name="dateOfBirth"/><br>
  (The following informations are optional.) <br>
  Gender (f or m): <input type="text" name="gender"/><br>
  Education: <input type="text" name="education"/><br>
  Home Ciry: <input type="text" name="homeCity"/><br>
  Home State: <input type="text" name="homeState"/><br>
  Home Country: <input type="text" name="homeCountry"/><br>
  Current City: <input type="text" name="currentCity"/><br>
  Current State: <input type="text" name="currentState"/><br>
  Current Country: <input type="text" name="currentCountry"/><br>
  <input type="submit" value="Create"/><br/>
</form>

<% }
   else { %>

<h2>Success!</h2>

<p>A new user has been created with email <%= newUserBean.getEmail() %>.
You can now return to the <a href="index.jsp">login page</a>.

<% } %>

<h4><a href="main.jsp">Return to Main Page</a></h4>

</body>
</html>
