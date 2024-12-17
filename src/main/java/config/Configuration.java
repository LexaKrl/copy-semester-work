package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class Configuration {


    public static Optional<Connection> getConnection() {
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
