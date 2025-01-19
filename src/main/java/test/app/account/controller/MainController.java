package test.app.account.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.app.account.controller.dto.CreateRequestDto;
import test.app.account.controller.dto.UpdateRequestDto;
import test.app.account.controller.dto.UserFilter;
import test.app.account.entity.User;
import test.app.account.service.UserService;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class MainController {

  private final UserService userService;

  @PostMapping("/{id}")
  public void create(@PathVariable Long id, @RequestBody CreateRequestDto dto) {
    userService.createUserData(id, dto);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @RequestBody UpdateRequestDto dto) {
    userService.updateUserData(id, dto);
  }

  @GetMapping
  public List<User> read(@RequestParam("offset") Integer offset,
      @RequestParam("limit") Integer limit, @RequestBody UserFilter userFilter) {
    return userService.findUsers(PageRequest.of(offset, limit), userFilter);
  }
}
