package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;

@WebListener
public class FlywayMigrationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String url = "jdbc:postgresql://postgres.railway.internal:5432/railway";
        String user = "postgres";
        String password = "QVsWAivPKWkscvEbLuLwbSiDlmZShnGW";

        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .load();
        flyway.migrate();

    }
}
