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
<%@ page import="edu.bu.cs.cs460.photoshare.NewCommentDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.ValuableUser" %>

<!-- 
  1. for each uid 
    2. obtain # of comment from top_com_users view
    3. obtain # of picture from going through each album uid owns
    	and add up from num_pic_counts view 
    4. insert into table TopUsers with uid and (2+3)
    5. select top 10 from TopUsers table
-->

<head><title>Top 10 Users</title></head>
<body>

<h2> List of Top 10 Users </h2>

<%
	NewUserDao newUserDao = new NewUserDao();
	NewCommentDao newCommentDao = new NewCommentDao();
	HasPicturesDao hasPicturesDao = new HasPicturesDao();
	ValuableUser valuableUser = new ValuableUser();
	
	valuableUser.deleteAll();
	
	List<Integer> uids = newUserDao.getAllUId();
	for(Integer uid : uids) {
		String user_name = newUserDao.getUsersName(uid);
		int totCom = newCommentDao.getTotalCom(uid);
		int totPic = hasPicturesDao.getTotalPic(uid);
		valuableUser.create(uid, totCom + totPic);
	}
	
	List<Integer> rankUIds = valuableUser.getRank();
	if(rankUIds.size() < 10){
		for(int i = 0; i < rankUIds.size(); i++) {
			int rankUId1 = rankUIds.get(i);
			String user_name1 = newUserDao.getUsersName(rankUId1);
%>
    		<h4>Rank <%=i+1%>. <a href="listuseralbum.jsp?user_id=<%=rankUId1%>"><%=user_name1%></a></h4> 

<%
		}
	} else {
		int j = 0;
		for(Integer rankUId2 : rankUIds) {
			if(j < 10) {
				String user_name2 =  newUserDao.getUsersName(rankUId2);
%>
				<h4>Rank <%=(j+1)%>. <a href="listuseralbum.jsp?user_id=<%=rankUId2%>"><%=user_name2%></a></h4>
<%
				j ++;
			} else{ 
				break; 
			}
		}
	}
%>			

</body>
</html>
