package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * A data access object (DAO) to handle the CommentUnderPic table
 * 
 * 
 * boolean create(int pic_id, int com_id)- insert new comment into the data
 * 
 * List<Integer> allComsId(int comunderpic_picture_id)- return a list of 
 *      comment id that belongs to the input picture id
 * 
 * boolean deletePid(int comUnderPic_picture_id)- delete data with given pic id
 * 
 */
public class CommentUnderPicDao {
  
 private static final String INSERT_COMMENT_UNDER_PIC_STMT = "INSERT INTO " +
   "\"commentUnderPic\" (comUnderPic_picture_id, comUnderPic_comment_id)" +
   "VALUES (?, ?)";
 
   private static final String GET_ALL_COM_ID_SPECIFIC_PIC_STMT = "SELECT " + 
    "comUnderPic_comment_id FROM \"commentUnderPic\" WHERE comunderpic_picture_id = ?";
 
   private static final String DELETE_PID_STMT = "DELETE FROM \"commentUnderPic\" WHERE " +
    "comunderpic_picture_id = ?";
  
 public boolean create(int pic_id, int com_id) {
  PreparedStatement stmt = null;
  Connection conn = null;
  try {
   conn = DbConnection.getConnection();
   stmt = conn.prepareStatement(INSERT_COMMENT_UNDER_PIC_STMT);
   stmt.setInt(1, pic_id);
   stmt.setInt(2, com_id);
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
 
 
 public List<Integer> allComsId(int comunderpic_picture_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    
    List<Integer> comsIds = new ArrayList<Integer>();
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ALL_COM_ID_SPECIFIC_PIC_STMT);
      stmt.setInt(1, comunderpic_picture_id);
      rs = stmt.executeQuery();
      while (rs.next()) {
        comsIds.add(rs.getInt(1));
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
    
    return comsIds;
  }
 
 public boolean deletePid(int comUnderPic_picture_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();

      stmt = conn.prepareStatement(DELETE_PID_STMT);
      stmt.setInt(1, comUnderPic_picture_id);
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
