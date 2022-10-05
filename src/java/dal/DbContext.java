/*
 * Xuan Son
 * 
 * Jan 17, 2022
 *
 */
package dal;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 *
 * @author Xuan Son
 */
public class DbContext {

    private static final String hostName = "localhost";
    private static final String userName = "root";
    private static final String password = "12345";
    private static final String dbName = "clinicbooking";
    private static final String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName +"?allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&requireSSL=false";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(connectionUrl, userName, password);

            System.out.println("oke");
            return conn;
        } catch (Exception ex) {
//            Logger.getLogger(DbContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Connection c = getConnection();
       

        if (c == null) {
            System.out.println("something wrong");
        } else {
            System.out.println("ok");
        }
    }
}
