package edu.bu.cs.cs460.photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * A bean that handles new tag data
 *
 * Original author G. Zervas <cs460tf@bu.edu>
 * Modified by Yvette Tsai (ytsai@bu.edu)
 */
public class NewTagBean {
  private String tagName = "";
  private int tagId;
  
  public int getTagId() {
    return tagId;
  }
  
  public void setTagId(int id) {
    tagId = id;
  }
  
  public String getName() {
    return tagName;
  }

  public void setTagName(String name) {
    tagName = name;
  }
}
