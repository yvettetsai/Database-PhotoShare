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
<%@ page import="edu.bu.cs.cs460.photoshare.NewAlbumDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.TagRecommendation" %>
<%@ page import="edu.bu.cs.cs460.photoshare.MyTagPicCount" %>

<!-- 
  1. get a list of ranked tag_id used by user
    2. cut the list into top 5 if the size is greater than 5
    3. for each pid in the database
    	int countTid = 0;
    	for each tag in the top 5 list
        	if pid and tid is associated countTid ++ 
            
        int totalTid = get number of all the tid associated with pid
		insert into a new table (pid, countTid, countTid/totalTid)
	4. get a list of ranked pic based on countTid
    5. get a list of ranked pic based on countTid/totalTid
    6. while(ranked pic based on countTid is > 1)
            int tempPic = pic01
    		int tempCount = pic01
            
    		if (tempCount > get(0)'s countTid)
            	remove pic01 fron the list
            	insert tempPic into a new list
                
            else if (tempCount == get(0)'s countTid )
            	
                int tempRatio1 = pic01's ratio
                int tempRatio2 = get(0)'s ratio
                
                if (temRatio1 < tempRatio2)
                	remove get(1) from the list
                    insert into the new list
   		remove the last element in the list and insert into the new list
	7. display picture based on the new list of ranking 	
-->

<html>
<head><title>Process You May Also Like Search</title></head>

<body>

<h2> You May Also Like The Following Pictures </h2>

<% 
	String err = null;
	boolean process = false;
	
	NewUserDao newUserDao = new NewUserDao();
	int uid = newUserDao.getID(request.getUserPrincipal().getName());

	// get a list of my tags ranked by number of pictures
	HasTagsDao hasTagsDao = new HasTagsDao();
	NewTagDao newTagDao = new NewTagDao();
	HasPicturesDao hasPicturesDao = new HasPicturesDao();
	MyTagPicCount myTagPicCount = new MyTagPicCount();
	NewAlbumDao newAlbumDao = new NewAlbumDao();
	
	myTagPicCount.drop();
	myTagPicCount.createTable();
	
	// a list of all tid in the data
	List<Integer> allTags = newTagDao.getAllTId();
	for(Integer tag : allTags) {
		// a list of pic associates with the tid
		List<Integer> allPics = hasTagsDao.allPicId(tag);
		int numPic = 0;
		for(Integer pic : allPics) {
			
			int tempAId = hasPicturesDao.getAlbumId(pic);
			// if such pic belongs to the user
			if(newAlbumDao.validAlbum(tempAId,uid)) {
				numPic ++;
			}
		}
		//out.println(tag);
		myTagPicCount.create(tag, numPic);
	}
	
	
	
	List<Integer> rankMyTag = myTagPicCount.getRankPIdCount();
	
	// we only need the top five from the list
	while (rankMyTag.size() > 5) {
		rankMyTag.remove(5);
	}

	PictureDao pictureDao = new PictureDao();
	TagRecommendation tagRecomDao = new TagRecommendation();
    List<Integer> pictureIds = pictureDao.allPicturesIds();
	
	tagRecomDao.deleteAll();
	
	// get the number of those tags and ratio amoung all tags that each picture is associates with
	for(Integer pid : pictureIds) {
		int num5Tid = 0;
		for(int i = 0; i < rankMyTag.size(); i++) {
			if(hasTagsDao.checkPidTid(pid, rankMyTag.get(i))) {
				num5Tid ++;
			}
		}
		int totalTid = hasTagsDao.countAllTId(pid);
		
		int ratio;
		if(num5Tid == 0) { ratio = 0; }
		else { ratio = totalTid/num5Tid; }
		
		tagRecomDao.create(pid, num5Tid, ratio);
	}
	
	List<Integer> tempRankPids = tagRecomDao.getRankPIdTId();
	List<Integer> rankPids = new ArrayList<Integer>();
	
	while (tempRankPids.size() > 1) {
		
		int tempPid1 = tempRankPids.get(0);
		int tempPid1Count = tagRecomDao.getCount(tempPid1);
		int tempPid2 = tempRankPids.get(1);
		int tempPid2Count = tagRecomDao.getCount(tempPid2);
		
		if (tempPid1Count > tempPid2Count) {
			tempRankPids.remove(0);
			rankPids.add(tempPid1);
		
		// if the count of two picture is equal, compared the ratio
		} else if (tempPid1Count == tempPid2Count) {
			int tempPid1Total = tagRecomDao.getRatio(tempPid1);
			int tempPid2Total = tagRecomDao.getRatio(tempPid2);
			
			if (tempPid1Total < tempPid2Total) {
				tempRankPids.remove(1);
				rankPids.add(tempPid2);
			}else {
				tempRankPids.remove(0);
				rankPids.add(tempPid1);
			}
		}	
	}
	rankPids.add(tempRankPids.get(0));
	process = true;
%>


<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<table>
<% 
	if (process) {  
		for (Integer rankPid : rankPids) {
			int aid = hasPicturesDao.getAlbumId(rankPid);
%>			

				<tr>
       		 		<td><a href="/photoshare/displayPicture.jsp?picture_id=<%=rankPid%>?album_id=<%=aid%>">
            		<img src="/photoshare/img?t=1&picture_id=<%=rankPid%>"/></a>
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
