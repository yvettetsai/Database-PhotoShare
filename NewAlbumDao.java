package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) to handle the Albums table
 *
 * boolean validAlbum(String name, int album_user_id)- return true when the album with NAME
 *      belongs to the user with ALBUM_USER_ID
 *
 * int getAlbumIdSpecificUser(int album_user_id, String name)- returns the ALBUM_ID with 
 *      the ALBUM_USER_ID and the album with NAME
 * 
 * boolean create(String name, int album_user_id)- return true up successful inseration 
 *      of album with NAME and ALBUM_USER_ID into the Album table
 *    
 * List<Integer> allAlbumsIds()- return a list of all album_id in the Album table
 * 
 * List<Integer> allMyAlbumsId(int album_user_id)- return a list of album_id belongs 
 *      to the specific user with ALBUM_USER_ID
 * 
 * String getAlbumName(int album_id)- return the album name with specific ALBUM_ID
 * 
 * int getAlbumId(int user_id)- 
 * 
 * boolean validAlbum(int album_id, int album_user_id)- return true when the album with
 *     ALBUM_ID belong to the user with ALBUM_USER_ID
 * 
 * int getAlbumId(String name, int album_user_id)- return the aid with specific name and
 *     album user id
 * 
 * boolean deleteAid(int album_id)- return true if the entry in the data with 
 *      speciic aid is deleted 
 * 
 * int albumNameExist(String name)- return number of albums with the input name
 * 
 */
public class NewAlbumDao {
  
  private static final String CHECK_ALBUM_NAME_STMT = "SELECT " +
    "COUNT(*) FROM \"Albums\" WHERE name = ? AND album_user_id = ?";
  
  private static final String GET_ALBUM_ID_SPECIFIC_USER_STMT = "SELECT \"album_id\"" +
    "FROM \"Albums\" WHERE \"album_user_id\" = ? AND \"name\" = ?";
  
  private static final String NEW_ALBUM_STMT = "INSERT INTO " +
    "\"Albums\" (name, album_user_id) VALUES (?, ?)";
  
  private static final String ALL_ALBUM_IDS_STMT = "SELECT \"album_id\"" +
    "FROM \"Albums\" ORDER BY \"album_id\" DESC";
  
  private static final String GET_ALL_ALBUM_ID_SPECIFIC_USER_STMT = "SELECT " + 
    "\"album_id\" FROM \"Albums\" WHERE \"album_user_id\" = ?";
  
  private static final String GET_ALBUM_NAME_STMT = "SELECT \"name\"" +
    "FROM \"Albums\" WHERE \"album_id\" = ?";
  
  private static final String GET_ALBUM_ID_STMT = "SELECT \"album_id\"" +
    "FROM \"Albums\" WHERE \"album_user_id\" = ?";
  
  private static final String CHECK_USER_ALBUM_STMT = "SELECT " +
    "COUNT(*) FROM \"Albums\" WHERE album_id = ? AND album_user_id = ?";
  
  private static final String GET_ALBUM_ID_SPECIFIC_NAME_STMT = "SELECT album_id "+
    "FROM \"Albums\" WHERE name = ? AND album_user_id = ?";
  
  private static final String DELETE_AID_STMT = "DELETE FROM \"Albums\" "+
    "WHERE album_id = ?";
  
  private static final String CHECK_ALBUM_NAME_EXIST_STMT = "SELECT album_id " +
    "FROM \"Albums\" WHERE name = ?";
  
  public boolean validAlbum(String name, int album_user_id)
  {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(CHECK_ALBUM_NAME_STMT);
      stmt.setString(1, name);
      stmt.setInt(2, album_user_id);
      rs = stmt.executeQuery();
      if (!rs.next()) {
        // Theoretically this can't happen, but just in case...
        return false;
      }
      
      int result = rs.getInt(1);
      
      if (result <= 0) {
        // This email does not exists in the database
        return false; 
      }
      
      try { stmt.close(); }
      catch (Exception e) { }
      
      return true;
    }catch (SQLException e) {
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
  
  public int getAlbumIdSpecificUser(int album_user_id, String name) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(GET_ALBUM_ID_SPECIFIC_USER_STMT);
      stmt.setInt(1, album_user_id);
      stmt.setString(2, name);
      rs = stmt.executeQuery();
      if (!rs.next()) {
        return -1;
      }
      
      return rs.getInt(1);
      
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
  
  
  public boolean create(String name, int album_user_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(NEW_ALBUM_STMT);
      stmt.setString(1, name);
      stmt.setInt(2, album_user_id);
      
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
  
  
  public List<Integer> allAlbumsIds() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> albumsIds = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(ALL_ALBUM_IDS_STMT);
      rs = stmt.executeQuery();
      while (rs.next()) {
        albumsIds.add(rs.getInt(1));
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
        try { stmt.close(); } catch (SQLException e) { ;
        }
        stmt = null;
      }
      if (conn != null) {
        try { conn.close(); } catch (SQLException e) { ;
        }
        conn = null;
      }
    }
    
    return albumsIds;
  }
  
  
  public List<Integer> allMyAlbumsId(int album_user_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> albumsIds = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ALL_ALBUM_ID_SPECIFIC_USER_STMT);
      stmt.setInt(1, album_user_id);
      rs = stmt.executeQuery();
      while (rs.next()) {
        albumsIds.add(rs.getInt(1));
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
        try { stmt.close(); } catch (SQLException e) { ;
        }
        stmt = null;
      }
      if (conn != null) {
        try { conn.close(); } catch (SQLException e) { ;
        }
        conn = null;
      }
    }
    
    return albumsIds;
  }
  
  
  public String getAlbumName(int album_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(GET_ALBUM_NAME_STMT);
      stmt.setInt(1, album_id);
      rs = stmt.executeQuery();
      String name = "";
      
      while (rs.next()) {
        name = rs.getString(1);
      }
      
      rs.close();
      rs = null;
      
      stmt.close();
      stmt = null;
      
      return name;
      
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
  
  
  public int getAlbumId(int user_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(GET_ALBUM_ID_STMT);
      stmt.setInt(1, user_id);
      rs = stmt.executeQuery();
      int album_id = -1;
      
      while (rs.next()) {
        album_id = rs.getInt(1);
      }
      
      rs.close();
      rs = null;
      
      stmt.close();
      stmt = null;
      
      return album_id;
      
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
  
  public boolean validAlbum(int album_id, int album_user_id)
  {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(CHECK_USER_ALBUM_STMT);
      stmt.setInt(1, album_id);
      stmt.setInt(2, album_user_id);
      rs = stmt.executeQuery();
      if (!rs.next()) {
        return false;
      }
      
      int result = rs.getInt(1);
      
      if (result <= 0) {
        return false; 
      }
      
      try { stmt.close(); }
      catch (Exception e) { }
      
      return true;
    }catch (SQLException e) {
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
  
  
  public int getAlbumId(String name, int album_user_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(GET_ALBUM_ID_SPECIFIC_NAME_STMT);
      stmt.setString(1, name);
      stmt.setInt(2, album_user_id);
      rs = stmt.executeQuery();
      if (!rs.next()) {
        return -1;
      }
      
      return rs.getInt(1);
      
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
  
  
  public boolean deleteAid(int album_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(DELETE_AID_STMT);
      stmt.setInt(1, album_id);
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
  
  
  public int albumNameExist(String name)
  {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    int i = 0;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(CHECK_ALBUM_NAME_EXIST_STMT);
      stmt.setString(1, name);
      rs = stmt.executeQuery();
      while (rs.next()) {
        rs.getInt(1);
        i ++;
      }
      
      try { stmt.close(); }
      catch (Exception e) { }
    }catch (SQLException e) {
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
    return i;
  } 
}
