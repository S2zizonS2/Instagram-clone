package my.dhlee.instagramclonebackend.user.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.stream.Stream;
import my.dhlee.instagramclonebackend.common.exception.ErrorCode;
import my.dhlee.instagramclonebackend.user.dto.request.AttemptRequest;
import my.dhlee.instagramclonebackend.user.dto.request.AttemptRequest.AttemptRequestBuilder;
import my.dhlee.instagramclonebackend.user.dto.request.BirthDateDto;
import my.dhlee.instagramclonebackend.user.dto.request.SignUpRequest;
import my.dhlee.instagramclonebackend.user.service.MailService;
import my.dhlee.instagramclonebackend.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WebMvcTest
class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private MailService mailService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String expectErrorField = "$..errors[?(@.field == '%s')]";
    private String nullFieldRequestBody;
    private AttemptRequestBuilder fillRequiredFieldRequestBuilder;


    @BeforeEach
    void beforeEach() throws Exception {
        nullFieldRequestBody = objectMapper.writeValueAsString(AttemptRequest.builder().build());
        fillRequiredFieldRequestBuilder = AttemptRequest.builder().fullname("fullname").username("username");
    }

    @DisplayName("성명(fullname) 항목은 필수 입력값이다.")
    @Test
    void fullnameCouldNotNull() throws Exception {
        mvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(nullFieldRequestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "fullname").exists())
            .andDo(print());
    }

    @DisplayName("이름(username) 항목은 필수 입력값이다.")
    @Test
    void usernameCouldNotNull() throws Exception {
        mvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(nullFieldRequestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "username").exists())
            .andDo(print());
    }

    @DisplayName("이메일(email)은 null 을 허용한다.")
    @Test
    void emailAllowedNull() throws Exception {
        mvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(nullFieldRequestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "email").isEmpty())
            .andDo(print());
    }

    @DisplayName("휴대폰번호(phoneNumber)는 null 을 허용한다.")
    @Test
    void phoneNumberAllowedNull() throws Exception {
        mvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(nullFieldRequestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "phoneNumber").isEmpty())
            .andDo(print());
    }

    @DisplayName("아이디 검증")
    @MethodSource("userIdProvider")
    @ParameterizedTest(name = "[{index}] {2}")
    void userIdValidation(String email, String phoneNumber, String errorCode, String displayName) throws Exception {
        final AttemptRequest createRequest = fillRequiredFieldRequestBuilder.email(email)
            .phoneNumber(phoneNumber)
            .build();

        final String requestBody = objectMapper.writeValueAsString(createRequest);

        mvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(errorCode))
            .andDo(print());
    }

    private static Stream<Arguments> userIdProvider() {
        return Stream.of(
            Arguments.of(null, null, ErrorCode.USER_ID_COULD_NOT_BE_NULL.getCode(),
                "이메일과 휴대폰번호가 모두 null 일 수 없다."),
            Arguments.of("test@test.com", "01012341234", ErrorCode.USER_ID_COULD_ONE_VALUE.getCode(),
                "이메일과 휴대폰번호 둘 중 하나만 입력해야 한다.")
        );

    }

    @DisplayName("비밀번호 입력시 영문 대,소문자, 숫자, 특수문자의 조합으로 8자 이상 12자 이하로 입력한다.")
    @MethodSource("wrongPasswordProvider")
    @ParameterizedTest(name = "[{index}] {1}")
    void passwordValidation2(String wrongPassword, String displayName) throws Exception {
        final AttemptRequest createRequest = fillRequiredFieldRequestBuilder.email("test@test.com")
            .password(wrongPassword)
            .build();

        final String requestBody = objectMapper.writeValueAsString(createRequest);

        mvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "password").exists())
            .andDo(print());
    }

    private static Stream<Arguments> wrongPasswordProvider() {
        return Stream.of(
            Arguments.of("testtest!@#", "숫자를 포함해야 한다."),
            Arguments.of("testtest123", "특수기호를 포함해야 한다."),
            Arguments.of("1234!@#$", "영문을 포함해야한다."),
            Arguments.of("ab12!@H", "8자미만(7자)으로 작성할 수 없다."),
            Arguments.of("abcd1234!@#$H", "12자초과(13자)로 작성할 수 없다.")
        );
    }


    @DisplayName("생일의 년도 유효성 검사")
    @MethodSource("birthDateProvider")
    @ParameterizedTest(name = "[{index}] {2}")
    void 생일(BirthDateDto birthDate, String errorCode, String displayName) throws Exception {

        final String requestBody = objectMapper.writeValueAsString(birthDate);

        mvc.perform(post("/api/v1/accounts/check_age_eligibility")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(errorCode))
            .andDo(print());
    }

    private static Stream<Arguments> birthDateProvider() {
        return Stream.of(
            Arguments.of(new BirthDateDto(1899, 12, 31), ErrorCode.INVALID_BIRTH_DATE.getCode(),
                "1900년 이후 출생자부터 가입이 가능하다."),
            Arguments.of(new BirthDateDto(2100, 1, 1), ErrorCode.INVALID_BIRTH_DATE.getCode(),
                "2100년 이전 출생자까지 가입이 가능하다."),
            Arguments.of(makeUnder14YearsOldFromToday(), ErrorCode.NOT_ELIGIBILITY_AGE.getCode(), "14세 이상만 가입이 가능하다.")

        );
    }

    private static BirthDateDto makeUnder14YearsOldFromToday() {
        final LocalDate now = LocalDate.now();
        final int targetYear = now.getYear() - 14;
        final int dayOfYear = now.getDayOfYear() + 1;

        final LocalDate under14YearsDate = LocalDate.ofYearDay(targetYear, dayOfYear);

        return new BirthDateDto(under14YearsDate.getYear(),
            under14YearsDate.getMonthValue(),
            under14YearsDate.getDayOfMonth());
    }

    @DisplayName("모든 입력이 정상적으로 이루어지면 회원가입을 진행하고 200 코드를 반환한다.")
    @Test
    void signUp() throws Exception {
        final SignUpRequest signUpRequest = SignUpRequest.builder()
            .email("test@test.com")
            .password("test1234@")
            .fullname("fullname")
            .username("username")
            .year(1992)
            .month(8)
            .day(17)
            .build();

        final String requestBody = objectMapper.writeValueAsString(signUpRequest);

        mvc.perform(post("/api/v1/accounts/sign-up")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andDo(print());
    }

}