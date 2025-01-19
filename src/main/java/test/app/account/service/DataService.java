package test.app.account.service;

import test.app.account.controller.dto.UpdateRequestDto;
import test.app.account.entity.User;

public interface DataService {

  void create(Long userId, UpdateRequestDto value);


  void update(Long userId, UpdateRequestDto value);


  void delete(Long userId, UpdateRequestDto value);

  default void processOperation(User user, UpdateRequestDto dto) {
    switch (dto.getOperation()) {
      case CREATE:
        create(user.getId(), dto);
      case UPDATE:
        update(user.getId(), dto);
      case DELETE:
        delete(user.getId(), dto);
    }
  }
}
