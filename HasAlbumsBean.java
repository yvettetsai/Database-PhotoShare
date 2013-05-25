package edu.bu.cs.cs460.photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HasAlbumsBean {
  private int hasAlbum_user_id = -1;
  private int hasAlbum_album_id = -1;
  
  public int getHasAlbumUserId() {
    return hasAlbum_user_id;
  }

  public int getHasAlbumAlbumId() {
    return hasAlbum_album_id;
  }
  
  public void setHasAlbumUserId(int uId) {
    hasAlbum_user_id = uId;
  }

   public void setHasAlbumAlbumId(int aId) {
    hasAlbum_album_id = aId;
  }
}
