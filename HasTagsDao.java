package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
 *  A DAO to access HasTags Relational Table
 * 
 * boolean create(int hastag_picture_id, int hastag_tag_id)- return true 
 *      if a new data is successfully inset into the data
 *
 * List<Integer> allTId(int pid)- return a list of tid that with specific
 *      input picture id
 * 
 * List<Integer> allPicId(int tid)- return a list of pic that with specific
 *      input tag id
 * 
 * boolean deletePid(int hastags_picture_id)- delete data with given pid
 * 
 * List<Integer> rankTId()- return a list of tid ranked in the decreasing 
 *      order of number of pid associated with 
 * 
 * List<Integer> rankTIdUId(int uid)- return a list of tid used by uid
 *      which ranked by the number of pid it associated with in decreasing
 *      order
 * 
 * boolean checkPidTid(int hastags_picture_id, int hastags_tag_id)- return true
 *     if such pair of pid & tid exists in the table
 * 
 * int countAllTId(int pid)- return number of tids that associates with pid
 * 
 * int countAllPId(int tid)- return number of pids that associates with tid
 * 
 */
public class HasTagsDao
{
  private static final String NEW_HASTAG_STMT = "INSERT INTO " +
    "\"hasTags\" (hastags_picture_id, hastags_tag_id)" +
    " VALUES (?, ?)";
  
  private static final String GET_ALL_TID_SPECIFIC_PID_STMT = "SELECT " + 
    "hastags_tag_id FROM \"hasTags\" WHERE hastags_picture_id = ?";
  
  
  private static final String GET_ALL_PID_SPECIFIC_TID_STMT = "SELECT " + 
    "hastags_picture_id FROM \"hasTags\" WHERE hastags_tag_id = ?";
  
  
  private static final String DELETE_PID_STMT = "DELETE FROM \"hasTags\" WHERE " +
    "hastags_picture_id = ?";
  
  private static final String GET_TAG_RANK_STMT = "SELECT hastags_tag_id, COUNT " +
    "(hastags_picture_id) AS total_pic FROM \"hasTags\" GROUP BY hastags_tag_id " +
    "ORDER BY total_pic DESC";
  
  private static final String GET_PID_STMT = "SELECT COUNT (*) FROM \"hasTags\" WHERE " +
    "hastags_picture_id = ? AND hastags_tag_id = ?";
  
  private static final String GET_TAG_RANK_UID_STMT = "SELECT hastags_tag_id, COUNT " +
    "(hastags_picture_id) AS total_pic FROM \"hasTags\", \"hasPictures\", " +
    "\"hasAlbums\" GROUP BY hastags_tag_id ORDER BY total_pic DESC " + 
    "WHERE hasAlbums_album_id = hasPictures_album_id AND " + 
    "hasPictures_picture_id = hasTags_picture_id AND hasAlbums_user_id = ?";
  
  private static final String CHECK_PID_TID_STMT = "SELECT COUNT (*) FROM " +
    "\"hasTags\" WHERE hastags_picture_id = ? AND hastags_tag_id = ?";
  
  private static final String COUNT_ALL_TID_SPECIFIC_PID_STMT = "SELECT " + 
    "COUNT (*) FROM \"hasTags\" WHERE hastags_picture_id = ?";

  private static final String COUNT_ALL_PID_SPECIFIC_TID_STMT = "SELECT " + 
    "COUNT (*) FROM \"hasTags\" WHERE hastags_tag_id = ?";
  
  public boolean create(int hastag_picture_id, int hastag_tag_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(NEW_HASTAG_STMT);
      stmt.setInt(1, hastag_picture_id);
      stmt.setInt(2, hastag_tag_id);
      
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
  
  public List<Integer> allTId(int pid) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> tIds = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ALL_TID_SPECIFIC_PID_STMT);
      stmt.setInt(1, pid);
      rs = stmt.executeQuery();
      while (rs.next()) {
        tIds.add(rs.getInt(1));
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
    
    return tIds;
  }
  
  public List<Integer> allPicId(int tid) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> picIds = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ALL_PID_SPECIFIC_TID_STMT);
      stmt.setInt(1, tid);
      rs = stmt.executeQuery();
      while (rs.next()) {
        picIds.add(rs.getInt(1));
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
    
    return picIds;
  }
  
  
  public boolean deletePid(int hastags_picture_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(DELETE_PID_STMT);
      stmt.setInt(1, hastags_picture_id);
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
  
  
  public List<Integer> rankTId() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> tIds = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_TAG_RANK_STMT);
      rs = stmt.executeQuery();
      while (rs.next()) {
        tIds.add(rs.getInt(1));
        rs.getInt(2);
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
    return tIds;
  }
  
  
  public boolean pidExistInTid(int pid, int tid)
  {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_PID_STMT);
      stmt.setInt(1, pid);
      stmt.setInt(2, tid);
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
  
  
  public List<Integer> rankTIdUId(int uid) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> tIds = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_TAG_RANK_UID_STMT);
      stmt.setInt(1, uid);
      rs = stmt.executeQuery();
      while (rs.next()) {
        tIds.add(rs.getInt(1));
        rs.getInt(2);
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
    return tIds;
  }
  
  
  public boolean checkPidTid(int hastags_picture_id, int hastags_tag_id)
  {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(CHECK_PID_TID_STMT);
      stmt.setInt(1, hastags_picture_id);
      stmt.setInt(2, hastags_tag_id);
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
  
  
  public int countAllTId(int pid) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    int tIds = 0;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(COUNT_ALL_TID_SPECIFIC_PID_STMT);
      stmt.setInt(1, pid);
      rs = stmt.executeQuery();
      while (rs.next()) {
        rs.getInt(1);
        tIds ++;
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
    
    return tIds;
  }

  public int countAllPId(int tid) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    int tIds = 0;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(COUNT_ALL_PID_SPECIFIC_TID_STMT);
      stmt.setInt(1, tid);
      rs = stmt.executeQuery();
      while (rs.next()) {
        rs.getInt(1);
        tIds ++;
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
    return tIds;
  }

}
