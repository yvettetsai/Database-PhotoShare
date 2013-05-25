package edu.bu.cs.cs460.photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.lang.String;

/**
 * A bean that handles new album data
 *
 * Original author G. Zervas <cs460tf@bu.edu>
 * Modified by Yvette Tsai (ytsai@bu.edu)
 */
public class NewAlbumBean {
  private String name = "";
  private int album_user_id = -1;

  public String getName() {
    return name;
  }

  public int getAlbumUserId() {
    return album_user_id;
  }

  public void setName(String n) {
    name = n.toLowerCase();
  }

  public void setAlbumUserId(int aid) {
    album_user_id = aid;
  }
}
