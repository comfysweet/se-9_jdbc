package se9;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ksu on 03.10.2016.
 */
public class DbHelper {
    private static Connection connection = null;
    private static DbHelper instance;
    private static String databaseUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String user = "PAYMENT1";
    private static String pwd = "PAYMENT1";

    public static DbHelper getInstance() {
        if (instance == null) {
            instance = new DbHelper();
        }
        return instance;
    }

    private DbHelper() {
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
            OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
            ocpds.setURL(databaseUrl);
            ocpds.setUser(user);
            ocpds.setPassword(pwd);
            PooledConnection pc = ocpds.getPooledConnection();
            connection = pc.getConnection();
            System.out.println("Connection Ok");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(databaseUrl, user, pwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    static void closeResource(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void closeConnection() {
        connection = null;
    }


}
