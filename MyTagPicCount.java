package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * boolean drop()- return true if table is drop successfullly
 * 
 * boolean createTable()- return true if table is created successfully
 * 
 * boolean create(int my_tag_id, int my_pic_count)- return true if the data 
 *      is inserted successfully
 * 
 *  List<Integer> getRankPIdCount()- return a list of ranking tid in the
 *      number of picture count it associated with
 * 
 */
public class MyTagPicCount { 
  private static final String CREATE_TABLE_STMT = "CREATE TABLE mytagpiccount " +
    "( my_tag_id int4 NOT NULL, my_pic_count int4 NOT NULL);";
  
  private static final String DROP_TABLE_STMT = "DROP TABLE mytagpiccount";
  
  private static final String INSERT_DATA_STMT = "INSERT INTO mytagpiccount " +
    "(my_tag_id, my_pic_count) VALUES (?, ?)";
  
  private static final String GET_RANK_TID_STMT = "SELECT my_tag_id FROM " +
    "mytagpiccount ORDER BY my_pic_count DESC";
  
  
  public boolean drop() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(DROP_TABLE_STMT);
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
      stmt = conn.prepareStatement(CREATE_TABLE_STMT);
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
  
  public boolean create(int my_tag_id, int my_pic_count) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(INSERT_DATA_STMT);
      stmt.setInt(1, my_tag_id);
      stmt.setInt(2, my_pic_count);
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
  
  
  public List<Integer> getRankPIdCount() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> tids = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_RANK_TID_STMT);
      rs = stmt.executeQuery();
      while (rs.next()) {
        tids.add(rs.getInt(1));
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
    return tids;
  }
}
