package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * A DAO to access TagRecommendation table
 * 
 * boolean create(int tag_recom_pid, int tag_recom_count, int tag_recom_total)- insert a new data 
 *      into the table, return true upon success
 * 
 * List<Integer> getRankPIdTId()- return a list of ranked pid based on count
 *      of specific tag ids
 * 
 * List<Integer> getRankPIdAll()- return a list of ranked pid based on ration of total
 *      tid and specific tag id 
 * 
 * int getRatio(int tag_recom_pid)- return the tag_recom_total based on the given pid
 * 
 * boolean deleteAll()- drop the valuablesuser
 * 
 * boolean create()- drop the valuablesuser
 * 
 * int getCount(int tag_recom_pid)- return the tag_recom_count based on the given pid
 * 
 */
public class TagRecommendation{
  
  private static final String INSERT_DATA_STMT = "INSERT INTO TagRecommendation " +
    "(tag_recom_pid, tag_recom_count, tag_recom_total) VALUES (?, ?, ?)";
  
  private static final String RANK_PIC_TID_STMT = "SELECT tag_recom_pid FROM " +
    "TagRecommendation ORDER BY tag_recom_count DESC";
  
  private static final String RANK_PIC_ALL_STMT = "SELECT tag_recom_pid FROM " +
    "TagRecommendation ORDER BY tag_recom_total DESC";
  
  private static final String DELETE_TABLE = "DELETE FROM TagRecommendation";
  
  private static final String CREATE_TABLE = "CREATE TABLE TagRecommendation " + 
    "(tag_recom_pid int4 NOT NULL, tag_recom_count int4 NOT NULL, " + 
    "tag_recom_total int4 NOT NULL);";
  
   private static final String GET_PID_TOTAL_STMT = "SELECT tag_recom_total FROM " +
    "TagRecommendation WHERE tag_recom_pid = ?";
   
   private static final String GET_PID_COUNT_STMT = "SELECT tag_recom_count FROM " +
    "TagRecommendation WHERE tag_recom_pid = ?";
  
  public boolean create(int tag_recom_pid, int tag_recom_count, int tag_recom_total) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(INSERT_DATA_STMT);
      stmt.setInt(1, tag_recom_pid);
      stmt.setInt(2, tag_recom_count);
      stmt.setInt(3, tag_recom_total);
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
  
  
  public List<Integer> getRankPIdTId() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> uids = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(RANK_PIC_TID_STMT);
      rs = stmt.executeQuery();
      while (rs.next()) {
        uids.add(rs.getInt(1));
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
    return uids;
  }

  public List<Integer> getRankPIdAll() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> uids = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(RANK_PIC_ALL_STMT);
      rs = stmt.executeQuery();
      while (rs.next()) {
        uids.add(rs.getInt(1));
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
    return uids;
  }
  
  public boolean deleteAll() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(DELETE_TABLE);
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
  
  public boolean createTable() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(CREATE_TABLE);
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
  
  
  public int getRatio(int tag_recom_pid) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(GET_PID_TOTAL_STMT);
      stmt.setInt(1, tag_recom_pid);
      rs = stmt.executeQuery();
     
      if (!rs.next()) {
        return -1;
      }
      
      int result = rs.getInt(1);
      
      rs.close();
      rs = null;
      
      stmt.close();
      stmt = null;
      
      return result;
      
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
  
  
  public int getCount(int tag_recom_pid) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      
      stmt = conn.prepareStatement(GET_PID_COUNT_STMT);
      stmt.setInt(1, tag_recom_pid);
      rs = stmt.executeQuery();
     
      if (!rs.next()) {
        return -1;
      }
      
      int result = rs.getInt(1);
      
      rs.close();
      rs = null;
      
      stmt.close();
      stmt = null;
      
      return result;
      
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
