package sample;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class ConnectDB extends Main {
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/SchoolDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EST";
        String username = "root";
        String password = "Zeal240101";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
            System.out.println("Database CONNECTED!!");
        } catch (Exception e) {
            System.err.println("Cannot connect the database :(");
            e.printStackTrace();
        }
        return conn;
    }
}
