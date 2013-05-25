package edu.bu.cs.cs460.photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HasTagsBean {
  private int hasTag_picture_id = -1;
  private int hasTag_tag_id = -1;
  
  public int getHasTagPicId() {
    return hasTag_picture_id;
  }

  public int getHasTagTagId() {
    return hasTag_tag_id;
  }
  
  public void setHasTagPicId(int pId) {
    hasTag_picture_id = pId;
  }

   public void setHasTagTagId(int tId) {
    hasTag_tag_id = tId;
  }
}
