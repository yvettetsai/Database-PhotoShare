package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*
 * A DAO to access ValuableUser table
 * 
 * boolean create(int val_user_id, int total_com, int total_pic)- insert a new data 
 *      into the table, return true upon success
 * 
 * List<Integer> getRank()- get the ranking of each user based on total
 * 
 * boolean deleteAll()- drop the valuablesuser
 * 
 * boolean create()- drop the valuablesuser
 * 
 */
public class ValuableUser {
  
  private static final String INSERT_DATA_STMT = "INSERT INTO valuableuser " +
    "(val_user_id, total) VALUES (?, ?)";
  
  private static final String RANK_USER_STMT = "SELECT val_user_id FROM " +
    "valuableuser ORDER BY total DESC";
  
  private static final String DELETE_TABLE = "DELETE FROM valuableuser";
  
  private static final String CREATE_TABLE = "CREATE TABLE valuableuser " + 
    "(val_user_id int4 not null, total int4 not null, " + 
    "CONSTRAINT uid_uq UNIQUE (val_user_id));";
  
  public boolean create(int val_user_id, int total) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(INSERT_DATA_STMT);
      stmt.setInt(1, val_user_id);
      stmt.setInt(2, total);
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
  
  
  public List<Integer> getRank() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> uids = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(RANK_USER_STMT);
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
}
