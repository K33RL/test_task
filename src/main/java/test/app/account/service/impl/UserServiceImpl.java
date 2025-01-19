package test.app.account.service.impl;

import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.app.account.controller.dto.CreateRequestDto;
import test.app.account.controller.dto.UpdateRequestDto;
import test.app.account.controller.dto.UserFilter;
import test.app.account.entity.DataType;
import test.app.account.entity.Email;
import test.app.account.entity.Phone;
import test.app.account.entity.User;
import test.app.account.repository.EmailRepository;
import test.app.account.repository.UserFilterRepository;
import test.app.account.repository.UserRepository;
import test.app.account.service.DataService;
import test.app.account.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final EmailRepository emailRepository;
  private final UserFilterRepository userFilterRepository;
  private final HashMap<DataType, DataService> dataServices;

  @Override
  @Transactional
  public void createUserData(String id, CreateRequestDto createRequestDto) {
    User user = userRepository.findById(Long.valueOf(id)).orElseThrow();
    if (createRequestDto.getEmail() != null) {
      Email.builder().userId(user.getId()).email(createRequestDto.getEmail()).build();
    }
    if (createRequestDto.getPhone() != null) {
      Phone.builder().userId(user.getId()).phone(createRequestDto.getPhone()).build();
    }
  }

  @Override
  @Transactional
  public void updateUserData(Long id, UpdateRequestDto requestDto) {
    User user = userRepository.findById(id).orElseThrow();
    dataServices.get(requestDto.getType()).processOperation(user, requestDto);
  }

  @Override
  public List<User> findUsers(PageRequest pageRequest, UserFilter userFilter) {
    return userFilterRepository.findAllByFilterPageable(userFilter, pageRequest);
  }

  @Override
  public User getByEmail(String email) {
    Email mail = emailRepository.findAllByEmail(email).orElseThrow();
    return userRepository.findById(mail.getUserId()).orElseThrow();
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow();
  }

  public UserDetailsService userDetailsService() {
    return this::getUserById;
  }

  private UserDetails getUserById(String s) {
    return getUserById(s);
  }
}
