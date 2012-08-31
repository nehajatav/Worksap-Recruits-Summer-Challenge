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
public class tableLogin {

    public static HashMap<String, String> populateLogins() {
        HashMap<String, String> loginDetails = new HashMap<String, String>();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            String dbURL = "jdbc:derby://localhost:1527/TwitterDB;create=true;user=admin;password=password";
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from LOGIN");
            while(results.next()){
                loginDetails.put(results.getString(1), results.getString(2));
            }
            stmt.close();
            
            conn.close();
            DriverManager.getConnection(dbURL + ";shutdown=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginDetails;
    }
    public static void addLoginDetails(String username, String Password){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            String dbURL = "jdbc:derby://localhost:1527/TwitterDB;create=true;user=admin;password=password";
            Connection conn = DriverManager.getConnection(dbURL);
            String sql = "insert into LOGIN values(?,?) ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, Password);
            pst.executeUpdate();
            
            pst.close();
            
            conn.close();
            DriverManager.getConnection(dbURL + ";shutdown=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
