package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * 
 * boolean create(int hasPictures_picture_id, int hasPictures_album_id)- it creates
 a relation data into hasPicture table
 * 
 * int getAlbumId(int haspictures_picture_id)- it returns the album id within a given picture id
 * 
 * boolean deletePid(int hasPictures_picture_id)- delete data from the table given picture id
 * 
 * List<Integer> allPicIds(int hasPictures_album_id)- return a list of pid that
 *      belongs to a speciic aid 
 * 
 */
public class HasPicturesDao
{
  private static final String NEW_HASPICTURE_STMT = "INSERT INTO " +
    "\"hasPictures\" (hasPictures_picture_id, hasPictures_album_id)" +
    " VALUES (?, ?)";
  
  private static final String GET_ALBID_SPECIFIC_PIC_STMT = "SELECT \"haspictures_album_id\"" +
    "FROM \"hasPictures\" WHERE haspictures_picture_id = ?";
  
  private static final String DELETE_PID_STMT = "DELETE FROM \"hasPictures\" WHERE " +
    "hasPictures_picture_id = ?";
  
  private static final String ALL_PICTURE_IDS_AID_STMT = "SELECT hasPictures_picture_id" +
    " FROM \"hasPictures\" WHERE hasPictures_album_id = ?";
  
  private static final String GET_TOTAL_PIC_STMT = "SELECT COUNT(haspictures_picture_id) "+
    "FROM \"Albums\", \"hasPictures\" WHERE album_user_id = ? AND album_id = haspictures_album_id";
  
  public int getAlbumId(int haspictures_picture_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ALBID_SPECIFIC_PIC_STMT);
      stmt.setInt(1, haspictures_picture_id);
      rs = stmt.executeQuery();
      
      if(!rs.next())
      {
        return -1;
      }
      
      return rs.getInt(1);
      
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (stmt != null) {
        try { stmt.close(); } catch (SQLException e) { ; }
        stmt = null;
      }
      if (conn != null) {
        try { conn.close(); } catch (SQLException e) { ; }
        conn = null;
      }
    }
  }
  
  
  public boolean create(int hasPictures_picture_id, int hasPictures_album_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(NEW_HASPICTURE_STMT);
      stmt.setInt(1, hasPictures_picture_id);
      stmt.setInt(2, hasPictures_album_id);
      stmt.executeUpdate();
      
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try { rs.close(); }
        catch (SQLException e) { ; }
        rs = null;
      }
      
      if (stmt != null) {
        try { stmt.close(); }
        catch (SQLException e) { ; }
        stmt = null;
      }
      
      if (conn != null) {
        try { conn.close(); }
        catch (SQLException e) { ; }
        conn = null;
      }
    }
  }
  
  
  public boolean deletePid(int hasPictures_picture_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(DELETE_PID_STMT);
      stmt.setInt(1, hasPictures_picture_id);
      stmt.executeUpdate();
      
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try { rs.close(); }
        catch (SQLException e) { ; }
        rs = null;
      }
      
      if (stmt != null) {
        try { stmt.close(); }
        catch (SQLException e) { ; }
        stmt = null;
      }
      
      if (conn != null) {
        try { conn.close(); }
        catch (SQLException e) { ; }
        conn = null;
      }
    }
  }
  
  
  public List<Integer> allPicIds(int hasPictures_album_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> picturesIds = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(ALL_PICTURE_IDS_AID_STMT);
      stmt.setInt(1, hasPictures_album_id);
      
      rs = stmt.executeQuery();
      while (rs.next()) {
        picturesIds.add(rs.getInt(1));
      }
      
      rs.close();
      rs = null;
      
      stmt.close();
      stmt = null;
      
      conn.close();
      conn = null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try { rs.close(); } catch (SQLException e) { ; }
        rs = null;
      }
      if (stmt != null) {
        try { stmt.close(); } catch (SQLException e) { ; }
        stmt = null;
      }
      if (conn != null) {
        try { conn.close(); } catch (SQLException e) { ; }
        conn = null;
      }
    }
    
    return picturesIds;
  }
  
  
  public int getTotalPic(int owner_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_TOTAL_PIC_STMT);
      stmt.setInt(1, owner_id);
      rs = stmt.executeQuery();
      
      if(!rs.next())
      {
        return -1;
      }
      
      return rs.getInt(1);
      
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (stmt != null) {
        try { stmt.close(); } catch (SQLException e) { ; }
        stmt = null;
      }
      if (conn != null) {
        try { conn.close(); } catch (SQLException e) { ; }
        conn = null;
      }
    }
  }
}
