package test.app.account.service.impl;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.app.account.entity.Account;
import test.app.account.repository.AccountRepository;
import test.app.account.service.AccountService;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  @Transactional
  public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
    Account fromAccount = accountRepository.findById(fromAccountId)
        .orElseThrow();
    Account toAccount = accountRepository.findById(toAccountId)
        .orElseThrow();

    BigDecimal fromAccountBalance = fromAccount.getBalance();
    BigDecimal toAccountBalance = toAccount.getBalance();
    fromAccount.setBalance(fromAccountBalance.subtract(amount));
    toAccount.setBalance(toAccountBalance.add(amount));

    accountRepository.save(fromAccount);
    accountRepository.save(toAccount);
  }
}
