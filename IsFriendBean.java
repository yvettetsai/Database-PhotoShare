package edu.bu.cs.cs460.photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class IsFriendsBean {
  
  private int user1 = -1;
  private int user2 = -1;

  public int getUser1() {
    return user1;
  }

   public int getUser2() {
    return user2;
  }
   
  public void setUser1(int u1) {
    user1 = u1;
  }

   public void setUser2(int u2) {
    user2 = u2;
  }
}
