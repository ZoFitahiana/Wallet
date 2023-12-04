package Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private final String url;
    private final String userName;
    private final String password;

    public ConnectionDB() {
        this.url = "jdbc:postgresql://" + System.getenv("DB_HOST") + ":" + System.getenv("DB_PORT") + "/" + System.getenv("DB_NAME");
        this.userName = System.getenv("DB_USERNAME");
        this.password = System.getenv("DB_PASSWORD");
    }

    public Connection createConnection() {
        try {
            return DriverManager.getConnection(
                    this.url, this.userName, this.password
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
