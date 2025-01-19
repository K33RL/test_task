package test.app.account.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import test.app.account.controller.dto.UpdateRequestDto;
import test.app.account.entity.Email;
import test.app.account.repository.EmailRepository;
import test.app.account.service.EmailService;

@Configuration
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final EmailRepository repository;

  @Override
  public void create(Long userId, UpdateRequestDto value) {
    if (isBinded(userId, value.getValue())) {
      repository.save(Email.builder().userId(userId).email(value.getValue()).build());
    }
  }

  @Override
  public void update(Long userId, UpdateRequestDto value) {
    if (isBinded(userId, value.getValue())) {
      Email email = repository.findAllByEmail(value.getValue()).orElseThrow();
      email.setEmail(value.getValue());
      repository.save(email);
    }
  }

  @Override
  public void delete(Long userId, UpdateRequestDto value) {
    if (isBinded(userId, value.getValue())) {
      Email email = repository.findAllByEmail(value.getValue()).orElseThrow();
      repository.deleteById(email.getId());
    }
  }

  private boolean isBinded(Long userId, String value) {
    Optional<Email> byEmail = repository.findAllByEmail(value);
    if (byEmail.isPresent()) {
      Email email = byEmail.get();
      return email.getUserId().equals(userId);
    } else {
      return false;
    }
  }
}
