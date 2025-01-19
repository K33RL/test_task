package test.app.account.controller;

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
public class AuthController {

  private final AuthenticationService authenticationService;

  @PostMapping("/sign-in")
  public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
    return authenticationService.signIn(request);
  }
}
