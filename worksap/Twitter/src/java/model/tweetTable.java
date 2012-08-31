/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.HashMap;

/**
 *
 * @author jneha
 */
public class tweetTable {

    public static HashMap<Integer, String> populateTweets() {
        HashMap<Integer, String> tweet_table = new HashMap<Integer, String>();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            String dbURL = "jdbc:derby://localhost:1527/TwitterDB;create=true;user=admin;password=password";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from TWEETS");
            while(results.next()){
                tweet_table.put(results.getInt(4), "@"+results.getString(1)+" posted at "+results.getTimestamp(3)+" :\n"+results.getString(2));
            }
            stmt.close();
            
            conn.close();
            DriverManager.getConnection(dbURL + ";shutdown=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tweet_table;
    }

    public static void deleteEntry(String parameter) {
        int id = Integer.parseInt(parameter);
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            String dbURL = "jdbc:derby://localhost:1527/TwitterDB;create=true;user=admin;password=password";
            Connection conn = DriverManager.getConnection(dbURL);
            String sql = "delete from TWEETS where id=? ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.executeUpdate();
            
            pst.close();
            
            conn.close();
            DriverManager.getConnection(dbURL + ";shutdown=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addEntry(String parameter, String parameter0) {
        HashMap<Integer, String> tweet_table = populateTweets();
        int nextID= tweet_table.size() +1;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            String dbURL = "jdbc:derby://localhost:1527/TwitterDB;create=true;user=admin;password=password";
            Connection conn = DriverManager.getConnection(dbURL);
            String sql = "insert into TWEETS values(?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,parameter);
            pst.setString(2,parameter0);
            pst.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            pst.setInt(4,nextID);
            pst.executeUpdate();
            pst.close();
            
            conn.close();
            DriverManager.getConnection(dbURL + ";shutdown=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
