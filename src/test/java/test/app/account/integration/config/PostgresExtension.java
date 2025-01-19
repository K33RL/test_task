package test.app.account.integration.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
public class PostgresExtension implements BeforeAllCallback, AfterAllCallback {

  private static PostgreSQLContainer<?> postgres;

  private static final String IMAGE_VERSION = "postgres:15.2";

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    if (postgres == null) {
      log.info("PostgreSQL container starting");
      postgres = configurePostgres();
      postgres.start();
      log.info("PostgreSQL container started");
      updateProperties();
    }
  }

  public static PostgreSQLContainer<?> configurePostgres() {
    return new PostgreSQLContainer<>(DockerImageName.parse(IMAGE_VERSION))
        .withDatabaseName("postgres")
        .withUsername("postgres")
        .withPassword("postgres")
        .withNetwork(Containers.NETWORK)
        .withNetworkAliases("postgres")
        .withInitScript("init.sql");
  }

  private void updateProperties() {
    System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
    System.setProperty("spring.datasource.username", postgres.getUsername());
    System.setProperty("spring.datasource.password", postgres.getPassword());
  }

  @Override
  public void afterAll(ExtensionContext context) {
    log.info("PostgreSQL container stopping...");
    // do nothing, Testcontainers handles container shutdown
  }
}
