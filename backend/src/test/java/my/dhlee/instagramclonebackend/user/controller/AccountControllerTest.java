package my.dhlee.instagramclonebackend.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import my.dhlee.instagramclonebackend.common.exception.ErrorCode;
import my.dhlee.instagramclonebackend.common.exception.InvalidValueException;
import my.dhlee.instagramclonebackend.docs.RestDocsConfig;
import my.dhlee.instagramclonebackend.user.dto.request.AttemptRequest;
import my.dhlee.instagramclonebackend.user.dto.request.AttemptRequest.AttemptRequestBuilder;
import my.dhlee.instagramclonebackend.user.dto.request.BirthDateDto;
import my.dhlee.instagramclonebackend.user.dto.request.SignUpRequest;
import my.dhlee.instagramclonebackend.user.dto.request.VerifyEmail;
import my.dhlee.instagramclonebackend.user.service.AccountService;
import my.dhlee.instagramclonebackend.user.service.MailService;
import my.dhlee.instagramclonebackend.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@Import(RestDocsConfig.class)
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest
class AccountControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private MailService mailService;
    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String expectErrorField = "$..errors[?(@.field == '%s')]";

    private AttemptRequestBuilder requestBuilder;

    @Autowired
    private RestDocumentationResultHandler restDocs;


    @BeforeEach
    void beforeEach(final WebApplicationContext context, RestDocumentationContextProvider provider) throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
            .alwaysDo(MockMvcResultHandlers.print())
            .alwaysDo(restDocs)
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .build();

        requestBuilder = AttemptRequest.builder()
            .email("test@test.com")
            .phoneNumber("01012341234")
            .username("username")
            .fullname("fullname")
            .password("test1234!@#$");
    }


    @DisplayName("사용자 이름(fullname)은 항목은 필수 입력값이다.")
    @Test
    void fullnameCouldNotNull() throws Exception {
        final AttemptRequest attemptRequest = requestBuilder.fullname(null)
            .build();

        final String requestBody = objectMapper.writeValueAsString(attemptRequest);

        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "fullname").exists())
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[].field").description("error field"),
                    fieldWithPath("errors[].message").description("field error message")
                )
            ));

    }

    @DisplayName("이름(username) 항목은 필수 입력값이다.")
    @Test
    void usernameCouldNotNull() throws Exception {

        final AttemptRequest attemptRequest = requestBuilder.username(null)
            .build();

        final String requestBody = objectMapper.writeValueAsString(attemptRequest);

        mockMvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "username").exists())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[].field").description("error field"),
                    fieldWithPath("errors[].message").description("field error message")
                )
            ));
    }

    @DisplayName("이메일(email)은 null 을 허용한다.")
    @Test
    void emailAllowedNull() throws Exception {
        final AttemptRequest attemptRequest = requestBuilder.email(null)
            .build();

        final String requestBody = objectMapper.writeValueAsString(attemptRequest);

        mockMvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                )
            ));
    }

    @DisplayName("휴대폰번호(phoneNumber)는 null 을 허용한다.")
    @Test
    void phoneNumberAllowedNull() throws Exception {
        final AttemptRequest attemptRequest = requestBuilder.phoneNumber(null)
            .build();

        final String requestBody = objectMapper.writeValueAsString(attemptRequest);

        mockMvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                )
            ));
    }

    @DisplayName("이메일과 휴대폰번호는 동시에 null 일 수 없다.")
    @Test
    void isNotAllowedBothNullEmailAndPhoneNumber() throws Exception {
        final AttemptRequest createRequest = requestBuilder.email(null)
            .phoneNumber(null)
            .build();

        final String requestBody = objectMapper.writeValueAsString(createRequest);

        mockMvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(ErrorCode.USER_ID_COULD_NOT_BE_NULL.getCode()))
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors").description("field error")
                )
            ));
    }

    @DisplayName("이메일과 휴대폰번호는 동시에 입력할 수 없다.")
    @Test
    void emailAndPhoneNumberCouldNotFilledBoth() throws Exception {
        final AttemptRequest createRequest = requestBuilder.build();

        final String requestBody = objectMapper.writeValueAsString(createRequest);

        mockMvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(ErrorCode.USER_ID_COULD_ONE_VALUE.getCode()))
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors").description("field error")
                )
            ));
    }

    @DisplayName("비밀번호 입력시 영문 대,소문자, 숫자, 특수문자의 조합으로 8자 이상 12자 이하로 입력한다. - 영문 누락")
    @Test
    void passwordCouldIncludeEnglish() throws Exception {
        final AttemptRequest createRequest = requestBuilder.password("1234!@#$")
            .build();

        final String requestBody = objectMapper.writeValueAsString(createRequest);

        mockMvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "password").exists())
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[].field").description("error field"),
                    fieldWithPath("errors[].message").description("error field message")
                )
            ));
    }

    @DisplayName("비밀번호 입력시 영문 대,소문자, 숫자, 특수문자의 조합으로 8자 이상 12자 이하로 입력한다. - 숫자 누락")
    @Test
    void passwordCouldIncludeNumber() throws Exception {
        final AttemptRequest createRequest = requestBuilder.password("abcd!@#$")
            .build();

        final String requestBody = objectMapper.writeValueAsString(createRequest);

        mockMvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "password").exists())
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[].field").description("error field"),
                    fieldWithPath("errors[].message").description("error field message")
                )
            ));
    }

    @DisplayName("비밀번호 입력시 영문 대,소문자, 숫자, 특수문자의 조합으로 8자 이상 12자 이하로 입력한다. - 특수문자 누락")
    @Test
    void passwordCouldIncludeSpecialCharacter() throws Exception {
        final AttemptRequest createRequest = requestBuilder.password("abcd1234")
            .build();

        final String requestBody = objectMapper.writeValueAsString(createRequest);

        mockMvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "password").exists())
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[].field").description("error field"),
                    fieldWithPath("errors[].message").description("error field message")
                )
            ));
    }

    @DisplayName("비밀번호 입력시 영문 대,소문자, 숫자, 특수문자의 조합으로 8자 이상 12자 이하로 입력한다. - 8자미만")
    @Test
    void passwordLengthCouldAtLeast8() throws Exception {
        final AttemptRequest createRequest = requestBuilder.password("abc123!")
            .build();

        final String requestBody = objectMapper.writeValueAsString(createRequest);

        mockMvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "password").exists())
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[].field").description("error field"),
                    fieldWithPath("errors[].message").description("error field message")
                )
            ));
    }

    @DisplayName("비밀번호 입력시 영문 대,소문자, 숫자, 특수문자의 조합으로 8자 이상 12자 이하로 입력한다. - 12자 초과")
    @Test
    void passwordLengthCouldNoMoreThan12() throws Exception {
        final AttemptRequest createRequest = requestBuilder.password("abcd1234!@#$H")
            .build();

        final String requestBody = objectMapper.writeValueAsString(createRequest);

        mockMvc.perform(post("/api/v1/accounts/attempt")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(expectErrorField, "password").exists())
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[].field").description("error field"),
                    fieldWithPath("errors[].message").description("error field message")
                )
            ));
    }


    @DisplayName("1900년 이후 출생자부터 가입 가능하다.")
    @Test
    void birthDateCouldAtLeast1900() throws Exception {

        final BirthDateDto birthDateDto = new BirthDateDto(1899, 12, 31);

        final String requestBody = objectMapper.writeValueAsString(birthDateDto);

        mockMvc.perform(post("/api/v1/accounts/check_age_eligibility")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(ErrorCode.INVALID_BIRTH_DATE.getCode()))
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("year").description("년도"),
                    fieldWithPath("month").description("월"),
                    fieldWithPath("day").description("일")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[]").description("error field")
                )
            ));
    }

    @DisplayName("2100년 이전 출생자까지 가입 가능하다.")
    @Test
    void birthDateCouldNoMoreThan2100() throws Exception {

        final BirthDateDto birthDateDto = new BirthDateDto(2100, 1, 1);

        final String requestBody = objectMapper.writeValueAsString(birthDateDto);

        mockMvc.perform(post("/api/v1/accounts/check_age_eligibility")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(ErrorCode.INVALID_BIRTH_DATE.getCode()))
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("year").description("년도"),
                    fieldWithPath("month").description("월"),
                    fieldWithPath("day").description("일")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[]").description("error field")
                )
            ));
    }

    @DisplayName("만 14세이상인 사람만 가입가능하다.")
    @Test
    void birthDateCouldAtLeast14YearsOld() throws Exception {

        final BirthDateDto birthDateDto = makeUnder14YearsOldFromToday();

        final String requestBody = objectMapper.writeValueAsString(birthDateDto);

        mockMvc.perform(post("/api/v1/accounts/check_age_eligibility")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(ErrorCode.NOT_ELIGIBILITY_AGE.getCode()))
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("year").description("년도"),
                    fieldWithPath("month").description("월"),
                    fieldWithPath("day").description("일")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[]").description("error field")
                )
            ));
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

    @DisplayName("유효하지 않은 이메일은 인증코드를 받을 수 없다.")
    @Test
    void invalidEmailCouldNotReceiveVerifyCode() throws Exception {
        final VerifyEmail verifyEmail = new VerifyEmail("deviceId", "test");

        final String requestBody = objectMapper.writeValueAsString(verifyEmail);

        mockMvc.perform(post("/api/v1/accounts/send_verify_email")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(ErrorCode.INVALID_VALUE.getCode()))
            .andExpect(jsonPath(expectErrorField, "email").exists())
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("deviceId").description("디바이스 아이디"),
                    fieldWithPath("email").description("인증코드를 받을 이메일"),
                    fieldWithPath("verifyCode").description("인증코드")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[].field").description("error field"),
                    fieldWithPath("errors[].message").description("error field message")
                )
            ));
    }

    @DisplayName("유효하지않은 인증코드를 입력할 수 없다.")
    @Test
    void invalidVerifyCode() throws Exception {
        final VerifyEmail verifyEmail = new VerifyEmail("deviceId", "test@test.com", "Ab3e91");

        final String requestBody = objectMapper.writeValueAsString(verifyEmail);

        willThrow(new InvalidValueException(ErrorCode.VERIFY_CODE_EXPIRED))
            .given(mailService)
            .verify(any(VerifyEmail.class));

        mockMvc.perform(post("/api/v1/accounts/check_confirmation_code")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(ErrorCode.VERIFY_CODE_EXPIRED.getCode()))
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("deviceId").description("디바이스 아이디"),
                    fieldWithPath("email").description("인증코드를 받을 이메일"),
                    fieldWithPath("verifyCode").description("인증코드")
                ),
                responseFields(
                    fieldWithPath("timestamp").description("current time"),
                    fieldWithPath("code").description("error code"),
                    fieldWithPath("message").description("error message"),
                    fieldWithPath("errors[]").description("error field")
                )
            ));
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

        mockMvc.perform(post("/api/v1/accounts/sign-up")
                .contentType(APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated())
            .andDo(print())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("email").description("이메일"),
                    fieldWithPath("phoneNumber").description("휴대폰 번호"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("username").description("성명"),
                    fieldWithPath("fullname").description("사용자 이름"),
                    fieldWithPath("year").description("생년월일 년도"),
                    fieldWithPath("month").description("월"),
                    fieldWithPath("day").description("일")
                )
            ));
    }
}