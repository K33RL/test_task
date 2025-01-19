package test.app.account.config;

import java.math.BigDecimal;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.app.account.repository.AccountRepository;

@Configuration
@RequiredArgsConstructor
public class CacheConfig {

  private final AccountRepository accountRepository;

  @Bean
  HashMap<Long, BigDecimal> accountStartBalances() {
    HashMap<Long, BigDecimal> balances = new HashMap<>();
    accountRepository.findAll()
        .forEach(account -> balances.put(account.getId(), account.getBalance()));
    return balances;
  }
}
