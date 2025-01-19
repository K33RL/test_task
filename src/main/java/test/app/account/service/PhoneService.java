package test.app.account.service;

import test.app.account.controller.dto.UpdateRequestDto;

public interface PhoneService extends DataService {
  void create(Long userId, UpdateRequestDto value);

  void update(Long userId, UpdateRequestDto value);

  void delete(Long userId, UpdateRequestDto value);
}

