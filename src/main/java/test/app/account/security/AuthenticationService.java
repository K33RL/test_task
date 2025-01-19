package test.app.account.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.app.account.controller.dto.JwtAuthenticationResponse;
import test.app.account.controller.dto.SignInRequest;
import test.app.account.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserService userService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  /**
   * Аутентификация пользователя
   *
   * @param request данные пользователя
   * @return токен
   */
  public JwtAuthenticationResponse signIn(SignInRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
    ));

    var user = userService.getByEmail(request.getEmail());

    var jwt = jwtService.generateToken(user);
    return new JwtAuthenticationResponse(jwt);
  }
}
