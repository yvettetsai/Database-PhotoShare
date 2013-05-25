package edu.bu.cs.cs460.photoshare;

public class CommentUnderPicBean {
  private int comUnderPic_picture_id = -1;
  private int comUnderPic_comment_id = -1;
  
  
  public void setPicId(int id) {
    comUnderPic_picture_id = id;
  }
  
  public int getPicId() {
    return comUnderPic_picture_id;
  }
  
  public void setComId(int id) {
    comUnderPic_comment_id = id;
  }
  
  public int getComId() {
    return comUnderPic_comment_id;
  }
}
                         
