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
 * A database organizes the user accounts in ChessHitsGame
 *
 * @author Yue Li
 */
public class UserDatabase {

    private String url = "jdbc:derby:ChessHitsUsersDB;create=true";
    private String DB_NAME = "CH_USER";
    private String usernameDerby = "hits";
    private String passwordDerby = "hits";
    Connection conn;

    /**
     * start connection with an existing database
     */
    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(url, usernameDerby, passwordDerby);
            System.out.println(url + "   connected....");

        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * create user account table
     */
    public void createTable() {
        try {
            Statement statement = conn.createStatement();
            String sqlCreate = "CREATE TABLE " + this.DB_NAME + "(ID VARCHAR(14), WINS INT, PWD VARCHAR(10))";
            statement.executeUpdate(sqlCreate);
            System.out.println("Table created");

        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * identify whether the user exists in the database
     *
     * @return true the user is a returning player, false otherwise
     */
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

    /**
     * match password with user input
     *
     * @param password the password that user typed in
     * @param account the account in the table
     * @return true if passwords match, false otherwise
     */
    public boolean matchPasswords(String password, String account) {
        boolean isPasswordValid = false;
        try {
            Statement statement = this.conn.createStatement();
            String selectAccountPwd = "SELECT PWD from " + this.DB_NAME + " where ID = '" + account + "'";
            ResultSet rs = statement.executeQuery(selectAccountPwd);
            while (rs.next()) {
                if (rs.getString(1).equals(password)) {
                    isPasswordValid = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isPasswordValid;
    }

    /**
     * does the account exist in the user table
     *
     * @param accountName the account to be searched in database
     * @return true if the account is found, false otherwise
     */
    public boolean doesAccountExist(String accountName) {
        boolean isFound = false;
        try {
            Statement statement = conn.createStatement();
            String selectComm = "SELECT ID from " + this.DB_NAME + " where ID = '" + accountName + "'";
            ResultSet rs = statement.executeQuery(selectComm);
            while (rs.next()) {
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

    /**
     * insert new user information to user table
     *
     * @param userName the accountName to be inserted
     * @param pwd the password of the account to be inserted
     */
    public void addNewUser(String userName, String pwd) {
        try {
            Statement statement = conn.createStatement();
            String sqlUpdate = "insert into " + this.DB_NAME + " values("
                    + " '" + userName + "' , " + 0 + ", '" + pwd + "')";
            statement.executeUpdate(sqlUpdate);
            statement.close();
            System.out.println("new user added");
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * retrieve how many wins of a player
     *
     * @param userName the user account to be searched in the table
     * @return true if the account is found, false otherwise
     */
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

    /**
     * increase a user's winning times by 1
     *
     * @param userName the winner
     */
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

    /**
     * close connection
     */
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
