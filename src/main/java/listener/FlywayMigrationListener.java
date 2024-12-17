package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

@Slf4j
@WebListener
public class FlywayMigrationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            String PGHOST = System.getenv("PGHOST");
            String PGPORT = System.getenv("PGPORT");
            String PGDATABASE = System.getenv("PGDATABASE");

            String url = "jdbc:postgresql://%s:%s/%s".formatted(PGHOST, PGPORT, PGDATABASE);

            String user = System.getenv("PGUSER");;
            String password = System.getenv("PGPASSWORD");

            Flyway flyway = Flyway.configure()
                    .dataSource(url, user, password)
                    .load();
            flyway.migrate();

            log.info("Migration passed successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}