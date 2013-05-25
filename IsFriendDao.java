package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IsFriendsDao
{
  private static final String NEW_FRIENDS_STMT = "INSERT INTO " +
    "\"isFriends\" (user_1, user_2) VALUES (?, ?)";
  
  private static final String GET_MY_FRIEND_STMT = "SELECT DISTINCT " +
    "\"user_2\" FROM \"isFriends\" WHERE \"user_1\" = ?"; 
  
  public List<Integer> allFriends(int user_1) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> friendsIds = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_MY_FRIEND_STMT);
      stmt.setInt(1, user_1);
      rs = stmt.executeQuery();
      while (rs.next()) {
        friendsIds.add(rs.getInt(1));
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
    
    return friendsIds;
  }
  
  
  
  
  public boolean create(int user_1, int user_2) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(NEW_FRIENDS_STMT);
      stmt.setInt(1, user_1);
      stmt.setInt(2, user_2);
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
