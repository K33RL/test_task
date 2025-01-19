package test.app.account.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import test.app.account.controller.dto.CreateRequestDto;
import test.app.account.integration.config.TestContainersIntegrationTest;
import test.app.account.repository.UserRepository;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class AccountApplicationMockMvcTest extends TestContainersIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  UserRepository userRepository;

  @Test
  @WithMockUser(value = "spring")
  @SneakyThrows
  public void testCreate() {
    CreateRequestDto createRequestDto = new CreateRequestDto();
    createRequestDto.setEmail("email@email.ru");
    String body = contentToString(createRequestDto);
    mvc.perform(post("/user/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body))
        .andExpect(status().isOk());
  }

  protected String contentToString(Object body) throws Exception {
    return objectMapper.writeValueAsString(body);
  }
}
