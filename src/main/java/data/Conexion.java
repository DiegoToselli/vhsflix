package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private static String user = "root";
    private static String pass = "root";
    private static String url = "jdbc:mysql://localhost:3310/vhsflix?useSSL=false&useTimeZone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";

    public static DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(pass);
        ds.setInitialSize(100);
        return ds;
    }

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return getDataSource().getConnection();
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(Statement st) {
        try {
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(Connection c) {
        try {
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
