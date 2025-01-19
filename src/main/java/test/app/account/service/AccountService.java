package test.app.account.service;

import java.math.BigDecimal;

public interface AccountService {

  public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
}
