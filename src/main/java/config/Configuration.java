package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class Configuration {

    private static Optional<Connection> connection = Optional.empty();

    public static Optional<Connection> getConnection() {
        if (connection.isEmpty()) {
            connection = getCon();
        }
        return connection;
    }

    public static Optional<Connection> getCon() {
        String PGHOST = System.getenv("PGHOST");
        String PGPASSWORD = System.getenv("PGPASSWORD");
        String PGPORT = System.getenv("PGPORT");
        String PGUSER = System.getenv("PGUSER");
        String PGDATABASE = System.getenv("PGDATABASE");
        try {
            Class.forName("org.postgresql.Driver");
            return Optional.ofNullable(DriverManager.getConnection(
                    "jdbc:postgresql://%s:%s/%s".formatted(PGHOST, PGPORT, PGDATABASE),
                    PGUSER,
                    PGPASSWORD
            ));
        } catch (ClassNotFoundException |
                 SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
