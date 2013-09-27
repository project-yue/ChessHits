/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userDB;

import java.sql.Connection;
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

    private final String url = "jdbc:derby://localhost:1527/ChessHitsUsersDB";
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
            String newTableName = "USER";
            String sqlCreate = "create table " + newTableName + " (ID varchar(20),"
                    + "WINS int)";

            String sqlUpdateTable = "update " + newTableName + " set price=15000 "
                    + "where brand='Toyota' and model='camry'";
            statement.executeUpdate(sqlUpdateTable);


            //statement.close();
            System.out.println("Table created");

        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewUser(String userName) {
        try {
            Statement statement = conn.createStatement();
            String userTable = "USER";
            String sqlUpdate = "insert into " + userTable + " values("
                    + " " + userName + 0 + ")";
            statement.executeUpdate(sqlUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateTable(String userName, int wins) {
        try {
            Statement statement = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getQuery() {
        ResultSet rs = null;

        try {

            System.out.println(" getting query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);



            String sqlQuery = "select model, price from car  "
                    + "where brand='Toyota'";

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
