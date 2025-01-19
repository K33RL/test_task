package test.app.account.service.impl;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import test.app.account.entity.Account;
import test.app.account.repository.AccountRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceIncreaseJob {

  private final AccountRepository accountRepository;
  private HashMap<Long, BigDecimal> accountStartBalances;

  @PostConstruct
  public void init() {
    accountStartBalances = new HashMap<>();
    accountRepository.findAll()
        .forEach(account -> accountStartBalances.put(account.getId(), account.getBalance()));
  }

  @Scheduled(cron = "*/30 * * * * *")
  public void everySecond() {
    List<Account> all = accountRepository.findAll();
    if (!all.isEmpty()) {
      all.forEach(this::increaseBalance);
    }
  }


  private void increaseBalance(Account account) {
    if (canIncrease(account)) {
      BigDecimal balance = account.getBalance();
      balance.multiply(BigDecimal.valueOf(1.1));
      account.setBalance(balance);
      accountRepository.save(account);
      log.info("Account balance increaser. Account: {}. Поток: {}",
          account.getId(), Thread.currentThread().getName());
    }
  }

  private boolean canIncrease(Account account) {
    BigDecimal currentBalance = account.getBalance();
    BigDecimal accountStartBalance = accountStartBalances.get(account.getId());
    BigDecimal result = currentBalance.divide(accountStartBalance)
        .multiply(BigDecimal.valueOf(100));
    return !(result.longValue() >= 207);
  }

}
