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
<%@ page import="edu.bu.cs.cs460.photoshare.TagSuggest"  %>

<html>
<head><title>Tag Recommendation for User</title></head>

<!--
  1. after obtain a lists of valid tag name in the system
	2. iterate through the list of valid TAG name
			find all the pid such TAG is assoicate 
			for each pid find the tid beside the one we had with
				insert the pair TAG, tid into the data base
	3. select the distict tid and display it
		SELECT DISTINCT tid, COUNT (TAG) AS total_tag FROM tagrecommendation GROUP BY TAG ORDER BY total_tag 
-->

<body>

<h2> Here is the list of recommended tag name  </h2>

<% 
	String err = null;
	boolean process = false;
	String inputTags = request.getParameter("tagString");
	String tempTags = request.getParameter("tagString");
	List<String> tagNames = new ArrayList<String>();
	
	while (tempTags.indexOf(' ') != -1) 
	{
		int i = tempTags.indexOf(' ');
		tagNames.add(tempTags.substring(0, i));
		tempTags = tempTags.substring(i+1);
	}
	tagNames.add(tempTags);
	
	NewTagDao newTagDao = new NewTagDao();	
		
	// test through all the tag name remove unexistence one		
	for(int i = 0; i < tagNames.size(); i++) 
	{
		if (newTagDao.getTagId(tagNames.get(i)) <= 0) 
		{
			tagNames.remove(i);
			i --;
		}
	}	
	
	if(tagNames.size() <= 0) 
	{
		err = "None of the input tag name exist in the website";
	}
	
	HasPicturesDao hasPicturesDao = new HasPicturesDao();
	HasTagsDao hasTagsDao = new HasTagsDao();
	TagSuggest tagSuggestDao = new TagSuggest();
	
	tagSuggestDao.deleteAll();
	
	for (int i = 0; i < tagNames.size(); i++) 
	{
		int tempInputTid = newTagDao.getTagId(tagNames.get(i));
		List<Integer> tempAllPids = hasTagsDao.allPicId(tempInputTid);
		for (int j = 0; j < tempAllPids.size(); j++) 
		{
			List<Integer> tempAllTids = hasTagsDao.allTId(tempAllPids.get(j));
			for(int k = 0; k < tempAllTids.size(); k++) 
			{
				int tid = tempAllTids.get(k);
				if(tid != tempInputTid) 
				{
					tagSuggestDao.insert(tempInputTid, tid);
				}
			}
		}
	}
	process = true;
%>


<% 
	if (err != null) 
	{ 
%>
		<font color=red><b>Error: <%=err%></b></font>
<%  } %>

<table>
<% 
	if (process) 
	{  
		List<Integer> suggestRankTags = tagSuggestDao.getRank();
		for (Integer suggestRankTag : suggestRankTags) 
		{
			boolean notMatch = true;
			String tagName = newTagDao.getTagName(suggestRankTag);
			for (int h = 0; h < tagNames.size(); h++) 
			{
				if ((tagNames.get(h)).equalsIgnoreCase(tagName)) 
				{
					notMatch = false;
					break;
				}
			}
			
			if (notMatch) 
			{
%>
        		<tr>
				<td><a href="displayPicTagName.jsp?tag_Id=<%=suggestRankTag%>"><%=tagName%></a></td>
        		<tr>
<%			}
        }
    }
%>
</table>

<% 
	if((!process) && (err == null)) 
	{ 
%>
		<h2>Failed...</h2>

		<p>Please try again!</p>

<% } %>


<h4>Return to <a href="index.jsp">Home Page</a></h4>
<h4>Return to <a href="main.jsp">Main Page</a></h4>

</body>
</html>
