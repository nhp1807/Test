package connect;
import java.sql.*;

public class Connect {
    private static String DB_URL = "jdbc:mysql://localhost:3306/database_quanlysinhvien";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    public static Connection conn;

    public static void main(String args[]) throws SQLException {
        try {
            // connnect to database 'testdb'
            conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from information");
            // show data
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getInt(2)
                        + "  " + rs.getString(3) + "  " + rs.getString(4)
                        + "  " + rs.getDouble(5) + "  " + rs.getInt(6));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection(String dbURL, String userName,
                                           String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            //System.out.println("connect successfully!");
        } catch (Exception ex) {
            //System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}