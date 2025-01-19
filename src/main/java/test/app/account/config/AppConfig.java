package test.app.account.config;

import java.util.HashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.app.account.entity.DataType;
import test.app.account.service.DataService;
import test.app.account.service.EmailService;
import test.app.account.service.PhoneService;

@Configuration
public class AppConfig {

  @Bean
  public HashMap<DataType, DataService> dataServices(EmailService emailService,
      PhoneService phoneService) {
    HashMap<DataType, DataService> services = new HashMap<>();
    services.put(DataType.EMAIL, emailService);
    services.put(DataType.PHONE, phoneService);
    return services;
  }
}
