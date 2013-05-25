package edu.bu.cs.cs460.photoshare;

public class NewCommentBean {
  private int comment_id;
  private String text = "";
  private int owner_id = -1;
  
  
  public void setCommentId(int id) {
    comment_id = id;
  }
  
  public int getCommentId() {
    return comment_id;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  public String getText() {
    return text;
  }
  
  public void setOwnerId(int id) {
    owner_id = id;
  }
  
  public int getOwnerId() {
    return owner_id;
  }
}
                         
