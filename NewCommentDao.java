package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*
 * A data access object (DAO) to handle the Comments table
 * 
 * boolean create(String text, int owner_id)- return true after successfully 
 *      insert new comment into the data
 * 
 * int getComId(String text, int owner_id)- return comment_id with 
 *      TEXT and OWNER_ID
 * 
 * String getText(int comment_id)- return the text with given comment id
 * 
 * int getOwnerId(int comment_id)- return the owner id with given comment id
 * 
 * boolean deletePid(int hastags_picture_id)- delete data with given pid
 * 
 */
public class NewCommentDao {
  
  private static final String INSERT_COMMENT_STMT = "INSERT INTO " +
    "\"Comments\" (text, owner_id) VALUES (?, ?)";
  
  private static final String GET_COM_ID_STMT = "SELECT \"comment_id\"" +
    " FROM \"Comments\" WHERE \"text\" = ? AND \"owner_id\" = ?";
  
  private static final String GET_COM_TEXT_STMT = "SELECT text" + 
    " FROM \"Comments\" WHERE comment_id = ?";

  private static final String GET_COM_OWNER_STMT = "SELECT owner_id" + 
    " FROM \"Comments\" WHERE comment_id = ?";
  
  private static final String GET_TOTAL_COM_STMT = "SELECT COUNT (comment_id) "+
    "FROM \"Comments\" WHERE owner_id = ?";
  
  public boolean create(String text, int owner_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(INSERT_COMMENT_STMT);
      stmt.setString(1, text);
      stmt.setInt(2, owner_id);
      stmt.executeUpdate();
      
      stmt.close();
      stmt = null;
 
      conn.close();
      conn = null;
      
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
    return true;
  }
  
  public int getComId(String text, int owner_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_COM_ID_STMT);
      stmt.setString(1, text);
      stmt.setInt(2, owner_id);
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
  
  
  public String getText(int comment_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_COM_TEXT_STMT);
      stmt.setInt(1, comment_id);
      rs = stmt.executeQuery();
      
      if(!rs.next())
      {
        return "";
      }
      
      return rs.getString(1);
      
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
  
  
  public int getOwnerId(int comment_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_COM_OWNER_STMT);
      stmt.setInt(1, comment_id);
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
  
  public int getTotalCom(int owner_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_TOTAL_COM_STMT);
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
