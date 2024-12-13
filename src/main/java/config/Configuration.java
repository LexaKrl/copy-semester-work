package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

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
        /*Properties properties = new Properties();
        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Class.forName("org.postgresql.Driver");

            return Optional.ofNullable(DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password")
            ));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Optional.empty();
        }*/
    }
}
