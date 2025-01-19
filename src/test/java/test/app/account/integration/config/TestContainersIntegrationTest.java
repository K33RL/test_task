package test.app.account.integration.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import test.app.account.AccountApplication;

@SpringBootTest(classes = {AccountApplication.class})
@Slf4j
@ExtendWith({PostgresExtension.class, SpringExtension.class})
public abstract class TestContainersIntegrationTest {}
