package test.app.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.app.account.controller.dto.JwtAuthenticationResponse;
import test.app.account.controller.dto.SignInRequest;
import test.app.account.security.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Название контроллера", description = "Описание контроллера")
public class AuthController {

  private final AuthenticationService authenticationService;

  @PostMapping("/sign-in")
  @Operation(summary = "Аутентификация пользователя", description = "Позволяет сгенерировать токен для пользователя")
  public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
    return authenticationService.signIn(request);
  }
}
