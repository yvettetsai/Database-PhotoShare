package edu.bu.cs.cs460.photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HasPicturesBean {
  private int hasPicture_picture_id = -1;
  private int hasPicture_album_id = -1;
  
  public int getHasPicturePictureId() {
    return hasPicture_picture_id;
  }

  public int getHasPictureAlbumId() {
    return hasPicture_album_id;
  }
  
  public void setHasPicturePictureId(int pId) {
    hasPicture_picture_id = pId;
  }

   public void setHasPictureAlbumId(int aId) {
    hasPicture_album_id = aId;
  }
}
