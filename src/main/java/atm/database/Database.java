package atm.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static atm.constant.Constants.MY_SQL_DRIVER;

public class Database {
    private static Connection connection;
    private Database() {}

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(MY_SQL_DRIVER);
        if (connection == null) {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ATM", "root", "951753258SSR");
        }
        return connection;
    }

}
