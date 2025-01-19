package test.app.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.app.account.controller.dto.TransferRequest;
import test.app.account.security.JwtService;
import test.app.account.service.AccountService;

@RestController
@RequestMapping(path = "/account")
@RequiredArgsConstructor
public class OperationController {

  private final AccountService accountService;
  private final JwtService jwtService;


  @PostMapping
  public ResponseEntity<HttpStatus> transfer(@RequestHeader(name = "Authorization") String token,
      @RequestBody TransferRequest transferRequest) {
    accountService.transfer(Long.valueOf(jwtService.extractUserId(token)),
        transferRequest.getAccountTo(), transferRequest.getAmount());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
