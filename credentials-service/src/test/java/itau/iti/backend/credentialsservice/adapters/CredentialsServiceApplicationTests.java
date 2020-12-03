package itau.iti.backend.credentialsservice.adapters;

import itau.iti.backend.credentialsservice.adapters.web.apis.CredentialsAPI;
import itau.iti.backend.credentialsservice.adapters.web.infrastructure.AppConfig;
import itau.iti.backend.credentialsservice.adapters.web.infrastructure.SpringSecurityConfig;
import itau.iti.backend.credentialsservice.domain.exceptions.ValidationException;
import itau.iti.backend.credentialsservice.domain.services.IValidatePasswordUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringJUnitWebConfig(TestAppConfig.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CredentialsServiceApplicationTests.class)
////@ActiveProfiles("test")
//@EnableAutoConfiguration
//@AutoConfigureMockMvc

@SpringJUnitWebConfig()
@WebMvcTest(CredentialsAPI.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {AppConfig.class, SpringSecurityConfig.class})

public class CredentialsServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IValidatePasswordUseCase mockService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
    }

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
//        assertThat(this.restTemplate.putForObject("http://localhost:" + port + "/validate",
//                String.class)).contains("Hello, World");
    }

    @Test
    public void validate_With_Valid_Parameter() throws Exception {

        mockMvc.perform(post("/validate").with(anonymous())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content("AbTp9!fok"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":true,\"additionalInfo\":null}"));
    }

    @Test
    public void validate_With_InvalidPassword() throws Exception {
        doThrow(ValidationException.class).when(mockService).validate("a");

        mockMvc.perform(post("/validate")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content("a"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string("{\"result\":false,\"additionalInfo\":\"InvalidPassword - Errors: [Min length]\"}"));

        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockService, times(1)).validate(passwordCaptor.capture());
    }

    @Test
    public void validate_With_NullPassword() throws Exception {

        mockMvc.perform(post("/validate")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().string("{\"result\":false,\"additionalInfo\":\"Password must be provided. - Errors: []\"}"));
    }

}
