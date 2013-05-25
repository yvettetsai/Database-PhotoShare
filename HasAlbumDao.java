package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
 *  A DAO to access HasAlbums Relational Table
 * 
 *  boolean create(int hasAlbum_user_id, int hasAlbum_album_id)- return true 
 *      if a new data is successfully inset into the data
 * 
 * int getUserId(int hasAlbum_user_id)- return user_id who own the hasAlbum_user_id 
 * 
 * boolean deleteAid(int hasAlbum_album_id, int hasAlbum_user_id)- return true is the data with 
 *      specific aid and uid is deleted
 * 
 */
public class HasAlbumsDao
{
  private static final String NEW_HASALBUM_STMT = "INSERT INTO " +
    "\"hasAlbums\" (hasAlbum_user_id, hasAlbum_album_id)" +
    " VALUES (?, ?)";
  
  private static final String GET_UID_STMT = "SELECT \"hasalbum_user_id\"" +
    " FROM \"hasAlbums\" WHERE \"hasalbum_album_id\" = ?";
  
  private static final String DELETE_AID_STMT = "DELETE FROM " +
    "\"hasAlbums\" WHERE hasalbum_album_id = ? AND hasAlbum_user_id = ?";
  
  public boolean create(int hasAlbum_user_id, int hasAlbum_album_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(NEW_HASALBUM_STMT);
      stmt.setInt(1, hasAlbum_user_id);
      stmt.setInt(2, hasAlbum_album_id);
      
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
  
  
  public int getUserId(int hasAlbum_album_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_UID_STMT);
      stmt.setInt(1, hasAlbum_album_id);
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
  
  
  public boolean deleteAid(int hasAlbum_album_id, int hasAlbum_user_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(DELETE_AID_STMT);
      stmt.setInt(1, hasAlbum_album_id);
      stmt.setInt(2, hasAlbum_user_id);
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
}
