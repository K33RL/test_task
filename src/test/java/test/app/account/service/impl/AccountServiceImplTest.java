package test.app.account.service.impl;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import test.app.account.entity.Account;
import test.app.account.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

  @Spy
  @InjectMocks
  AccountServiceImpl subj;
  @Mock
  AccountRepository accountRepository;

  private InOrder inOrder;

  @BeforeEach
  void setup() {
    inOrder = inOrder(subj, accountRepository);
  }

  @Test
  void transfer_success() {
    Long fromId = 15L;
    Long toId = 13L;
    Account from = Account.builder().id(1111L).userId(fromId).balance(BigDecimal.valueOf(1000))
        .build();
    Account to = Account.builder().id(111113L).userId(toId).balance(BigDecimal.valueOf(1000))
        .build();
    doReturn(Optional.of(from)).when(accountRepository).findByUserId(fromId);
    doReturn(Optional.of(to)).when(accountRepository).findByUserId(toId);

    subj.transfer(fromId, toId, BigDecimal.valueOf(500));

    inOrder.verify(subj, times(1)).transfer(fromId, toId, BigDecimal.valueOf(500));
    inOrder.verify(accountRepository, times(1)).findByUserId(fromId);
    inOrder.verify(accountRepository, times(1)).findByUserId(toId);
    inOrder.verify(subj, times(1)).canTransfer(any(), any());
    inOrder.verify(accountRepository, times(2)).save(any());
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void transfer_failure() {
    Long fromId = 15L;
    Long toId = 13L;
    Account from = Account.builder().id(1111L).userId(fromId).balance(BigDecimal.valueOf(1))
        .build();
    Account to = Account.builder().id(111113L).userId(toId).balance(BigDecimal.valueOf(1000))
        .build();
    doReturn(Optional.of(from)).when(accountRepository).findByUserId(fromId);
    doReturn(Optional.of(to)).when(accountRepository).findByUserId(toId);

    subj.transfer(fromId, toId, BigDecimal.valueOf(500));

    inOrder.verify(subj, times(1)).transfer(fromId, toId, BigDecimal.valueOf(500));
    inOrder.verify(accountRepository, times(1)).findByUserId(fromId);
    inOrder.verify(accountRepository, times(1)).findByUserId(toId);
    inOrder.verify(subj, times(1)).canTransfer(any(), any());
    inOrder.verify(accountRepository, never()).save(any());
    inOrder.verifyNoMoreInteractions();
  }
}