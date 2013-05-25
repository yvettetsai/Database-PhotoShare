package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * A data access object (DAO) to handle the Tags table
 *
 * boolean create(String tag_name)- return true when a new tag with
 *      tag_name is insert into the tags table
 * 
 * int getTagId(String tag_name)- return the TAG_ID with input tag name
 *      if not exist, return -1
 * 
 * String getTagName(String tag_id)- return the tag_name with input
 *      tag_id
 * 
 * List<Integer> getAllTId()- return a list of tid in the system
 * 
 */
public class NewTagDao {
  
  private static final String NEW_TAG_STMT = "INSERT INTO " +
      "\"Tags\" (tag_name) VALUES (?)";
  
  private static final String GET_TAG_ID_STMT = "SELECT tag_id" +
    " FROM \"Tags\" WHERE tag_name = ?";
  
  private static final String GET_TAG_NAME_STMT = "SELECT tag_name" +
    " FROM \"Tags\" WHERE tag_id = ?";
  
  private static final String GET_ALL_TID_STMT = "SELECT DISTINCT tag_id " +
    "FROM \"Tags\"";
  
  public boolean create(String tag_name) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(NEW_TAG_STMT);
      stmt.setString(1, tag_name);
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
  
  public int getTagId(String tag_name) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_TAG_ID_STMT);
      stmt.setString(1, tag_name);
      rs = stmt.executeQuery();
      
      if (!rs.next()) {
        return -1;
      }
      
      int tid = rs.getInt(1);
      
      if (tid <= 0) {
        return -1;
      }

      return tid;
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
  
  public String getTagName(int tag_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_TAG_NAME_STMT);
      stmt.setInt(1, tag_id);
      rs = stmt.executeQuery();
      
      if (!rs.next()) {
        return null;
      }

      return rs.getString(1);
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
  
  
  public List<Integer> getAllTId() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> tIds = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ALL_TID_STMT);
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
}
