/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.userDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author makingitbettergo
 */
public class UserDatabase {
    
    private String url = "jdbc:derby:ChessHitsUsersDB;create=true";
    private String DB_NAME = "CH_USER";
    private String usernameDerby = "hits";
    private String passwordDerby = "hits";
    Connection conn;
    
    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(url, usernameDerby, passwordDerby);
            System.out.println(url + "   connected....");
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void createTable() {
        try {
            Statement statement = conn.createStatement();
            String sqlCreate = "CREATE TABLE " + this.DB_NAME + "(ID VARCHAR(20), WINS INT)";
            statement.executeUpdate(sqlCreate);
            //statement.close();
            System.out.println("Table created");
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean doesUserTableExist() {
        boolean result = false;
        try {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, this.DB_NAME, null);
            if (tables.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean doesAccountExist(String accountName) {
        boolean isFound = false;
        try {
            Statement statement = conn.createStatement();
            String selectComm = "SELECT ID from " + this.DB_NAME + " where ID = '" + accountName + "'";
            ResultSet rs = statement.executeQuery(selectComm);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                if (rs.getString(1).equals(accountName)) {
                    isFound = true;
                }
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isFound;
    }
    
    public void addNewUser(String userName) {
        try {
            Statement statement = conn.createStatement();
            String sqlUpdate = "insert into " + this.DB_NAME + " values("
                    + " '" + userName + "' , " + 0 + ")";
            statement.executeUpdate(sqlUpdate);
            statement.close();
            System.out.println("new user added");
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int getWins(String userName) {
        int wins = -1;
        try {
            Statement st = conn.createStatement();
            String sqlUpdate = "select WINS from " + this.DB_NAME + " where ID = '" + userName + "'";
            ResultSet rs = st.executeQuery(sqlUpdate);
            while (rs.next()) {
                wins = rs.getInt("WINS");
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return wins;
    }
    
    public void increaseWins(String userName) {
        int currentWins = getWins(userName);
        currentWins++;
        try {
            Statement st = conn.createStatement();
            String sqlUpdate = "update " + this.DB_NAME + " set WINS = " + currentWins + " where ID = '" + userName + "'";
            st.executeUpdate(sqlUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getQuery(String accountName) {
        ResultSet rs = null;
        try {
            System.out.println(" getting user query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = "select ID, from " + "where brand='Toyota'";
            rs = statement.executeQuery(sqlQuery);
            rs.beforeFirst();
            while (rs.next()) {
                String model = rs.getString("model"); // 1
                int price = rs.getInt(2);
                System.out.println(model + ":  $" + price);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        //return(rs);  
    }
    
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
