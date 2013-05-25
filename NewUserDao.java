package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) to handle the Users table
 *
 * Original author G. Zervas <cs460tf@bu.edu>
 * Modified by Yvette Tsai (ytsai@bu.edu)
 * 
 * boolean validUser(String email)- varify whether a user exists in the database
 * 
 * boolean create(String email, String password, String fname, String lname, 
 * String dob, int gender, String edu, String hCi, String hS, String hCo, 
 * String cCi, String cS, String cCo)- insert new user into the data
 * int getID(String email)- get the user's ID from his email
 * String getUsersName(int user_id)- get uer's name from his user_id
 * 
 */
public class NewUserDao {
  private static final String CHECK_EMAIL_STMT = "SELECT " +
    "COUNT(*) FROM \"Users\" WHERE email = ?";
  
  private static final String NEW_USER_STMT = "INSERT INTO " +
    "\"Users\" (email, password, fname, lname, dob, gender," + 
    "edu, hCity, hState, hCountry, cCity, cState, cCountry)" +
    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  
  private static final String GET_ID_STMT = "SELECT \"user_id\"" +
    "FROM \"Users\" WHERE email = ?";
  
  private static final String GET_NAME_STMT = "SELECT \"email\"" +
    "FROM \"Users\" WHERE user_id = ?";
  
  private static final String GET_ALL_USER_STMT = "SELECT user_id " + 
    "FROM \"Users\"";
  
  /*
   * validUser()- Check whether an user exits in the database
   */
  public boolean validUser(String email)
  {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(CHECK_EMAIL_STMT);
      stmt.setString(1, email);
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
  
  
  
  
  /*
   * create()- Insert new user info into the database
   */
  public boolean create(String email, String password, 
                        String fname, String lname, String dob,
                        int gender, String edu, String hCi, String hS,
                        String hCo, String cCi, String cS, String cCo) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(CHECK_EMAIL_STMT);
      stmt.setString(1, email);
      rs = stmt.executeQuery();
      if (!rs.next()) {
        // Theoretically this can't happen, but just in case...
        return false;
      }
      int result = rs.getInt(1);
      if (result > 0) {
        // This email is already in use
        return false; 
      }
      
      try { stmt.close(); }
      catch (Exception e) { }
      
      stmt = conn.prepareStatement(NEW_USER_STMT);
      stmt.setString(1, email);
      stmt.setString(2, password);
      stmt.setString(3, fname);
      stmt.setString(4, lname);
      stmt.setString(5, dob);
      stmt.setInt(6, gender);
      stmt.setString(7, edu);
      stmt.setString(8, hCi);
      stmt.setString(9, hS);
      stmt.setString(10, hCo);
      stmt.setString(11, cCi);
      stmt.setString(12, cS);
      stmt.setString(13, cCo);
      
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
  
  
  public int getID(String email) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ID_STMT);
      stmt.setString(1, email);
      rs = stmt.executeQuery();
      if (!rs.next()) {
        // Theoretically this can't happen, but just in case...
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
  
  
  public String getUsersName(int user_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_NAME_STMT);
      stmt.setInt(1, user_id);
      rs = stmt.executeQuery();
      if (!rs.next()) {
        // Theoretically this can't happen, but just in case...
        return "";
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
  
  public List<Integer> getAllUId() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> uids = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ALL_USER_STMT);
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
}
