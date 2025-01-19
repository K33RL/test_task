package test.app.account.service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import test.app.account.controller.dto.CreateRequestDto;
import test.app.account.controller.dto.UpdateRequestDto;
import test.app.account.controller.dto.UserFilter;
import test.app.account.entity.User;

public interface UserService {

  void createUserData(String id, CreateRequestDto createRequestDto);

  void updateUserData(Long id, UpdateRequestDto createDto);

  List<User> findUsers(PageRequest pageRequest, UserFilter userFilter);

  User getByEmail(String email);

  User getUserById(Long id);
}
