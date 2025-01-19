package test.app.account.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import test.app.account.controller.dto.UpdateRequestDto;
import test.app.account.entity.Phone;
import test.app.account.repository.PhoneRepository;
import test.app.account.service.PhoneService;

@Component
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

  private final PhoneRepository repository;

  @Override
  public void create(Long userId, UpdateRequestDto value) {
    if (isBinded(userId, value.getValue())) {
      repository.save(Phone.builder().userId(userId).phone(value.getValue()).build());
    }
  }

  @Override
  public void update(Long userId, UpdateRequestDto value) {
    if (isBinded(userId, value.getValue())) {
      Phone phone = repository.findByPhone(value.getValue()).orElseThrow();
      phone.setPhone(value.getValue());
      repository.save(phone);
    }
  }

  @Override
  public void delete(Long userId, UpdateRequestDto value) {
    if (isBinded(userId, value.getValue())) {
      Phone phone = repository.findByPhone(value.getValue()).orElseThrow();
      repository.deleteById(phone.getId());
    }
  }

  private boolean isBinded(Long userId, String value) {
    Optional<Phone> byPhone = repository.findByPhone(value);
    if (byPhone.isPresent()) {
      Phone phone = byPhone.get();
      return phone.getUserId().equals(userId);
    } else {
      return false;
    }
  }
}
