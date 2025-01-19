package test.app.account.repository;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import test.app.account.controller.dto.UserFilter;
import test.app.account.entity.User;

public interface UserFilterRepository {

  List<User> findAllByFilterPageable(UserFilter filter, PageRequest pageRequest);
}
