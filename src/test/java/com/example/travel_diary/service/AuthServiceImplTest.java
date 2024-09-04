package com.example.travel_diary.service;

import com.example.travel_diary.global.domain.entity.User;
import com.example.travel_diary.global.domain.repository.UserRepository;
import com.example.travel_diary.global.exception.EmailAlreadyExistsException;
import com.example.travel_diary.global.exception.LoginFailedException;
import com.example.travel_diary.global.exception.LoginIdAlreadyExistsException;
import com.example.travel_diary.global.exception.UserNotFoundException;
import com.example.travel_diary.global.request.FindLoginIdRequestDto;
import com.example.travel_diary.global.request.SignInRequestDto;
import com.example.travel_diary.global.request.SignUpRequestDto;
import com.example.travel_diary.global.response.GetUserByIdResponseDto;
import com.example.travel_diary.global.response.LoginInResponseDto;
import com.example.travel_diary.global.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthServiceImplTest {
    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user;


    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(UUID.randomUUID())
                .loginId("testLoginId")
                .name("testName")
                .nickname("testNickname")
                .password(passwordEncoder.encode("testPassword"))
                .dateOfBirth(LocalDate.now())
                .email("testEmail")
                .createdAt(LocalDateTime.now())
                .posts(new ArrayList<>())
                .build();
        userRepository.save(user);
    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    void signUp_Success() throws Exception {
        //given
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("testLoginId2", "testName2", "testNickname2", "testPassword2", LocalDate.now(), "testEmail2");

        //when
        UUID uuid = authService.signUp(signUpRequestDto);

        // then
        Optional<User> byId = userRepository.findById(uuid);
        assertTrue(byId.isPresent());
        assertEquals("testLoginId2", byId.get().getLoginId());
        assertEquals("testName2", byId.get().getName());
        assertEquals("testNickname2", byId.get().getNickname());
        assertEquals("testEmail2", byId.get().getEmail());
    }

    @Test
    void signUp_LoginIdAlreadyExists() {
        //given
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("testLoginId", "testName2", "testNickname2", "testPassword2", LocalDate.now(), "testEmail2");

        //when & then
        assertThrows(LoginIdAlreadyExistsException.class, () -> {
            authService.signUp(signUpRequestDto);
        });
    }

    @Test
    void signUp_EmailAlreadyExists() {
        //given
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("testLoginId2", "testName2", "testNickname2", "testPassword2", LocalDate.now(), "testEmail");

        //when & then
        assertThrows(EmailAlreadyExistsException.class, () -> {
            authService.signUp(signUpRequestDto);
        });
    }

    @Test
    void signIn_Success() throws Exception {
        SignInRequestDto signInRequestDto = new SignInRequestDto("testLoginId", "testPassword");
        LoginInResponseDto responseDto = authService.signIn(signInRequestDto);
        assertEquals(user.getId(), responseDto.loginId());
        assertEquals(user.getNickname(), responseDto.nickname());
        assertNotNull(responseDto.token());
    }

    @Test
    void signIn_UserNotFound() {
        SignInRequestDto signInRequestDto = new SignInRequestDto("testLoginId3", "testPassword");
        assertThrows(UserNotFoundException.class, () -> {
            authService.signIn(signInRequestDto);
        });
    }

    @Test
    void signIn_InvalidPassword() {
        SignInRequestDto signInRequestDto = new SignInRequestDto("testLoginId", "testPassword3");
        assertThrows(LoginFailedException.class, () -> {
            authService.signIn(signInRequestDto);
        });
    }

    @Test
    void possibleUserByEmail_AlreadyExist() {
        String email = "testEmail";
        assertThrows(EmailAlreadyExistsException.class, () -> {
            authService.possibleUserByEmail(email);
        });
    }

    @Test
    void possibleUserByEmail_Possible() throws Exception {
        String email = "newEmail";
        String result = authService.possibleUserByEmail(email);
        assertEquals("possible", result);
    }

    @Test
    void possibleUserByLoginId_AlreadyExist() {
        String loginId = "testLoginId";
        assertThrows(LoginIdAlreadyExistsException.class, () -> {
            authService.possibleUserByLoginId(loginId);
        });
    }

    @Test
    void possibleUserByLoginId_Possible() throws Exception {
        String loginId = "newLoginId";
        String result = authService.possibleUserByLoginId(loginId);
        assertEquals("possible", result);
    }

    @Test
    void getUserById_Success() {
        GetUserByIdResponseDto userById = authService.getUserById(user.getId());
        assertEquals("testNickname", userById.nickname());
        assertTrue(passwordEncoder.matches("testPassword", userById.password()));
        assertEquals("testEmail", userById.email());
    }

    @Test
    void getUserById_UserNotFound() {
        UUID testUUID = UUID.randomUUID();
        assertThrows(UserNotFoundException.class, () -> {
            authService.getUserById(testUUID);
        });
    }


    @Test
    void updateUserNickname_Success() {
        authService.updateUserNickname(user.getId(), "changeNickname");
        Optional<User> byId = userRepository.findById(user.getId());
        assertEquals("changeNickname", byId.get().getNickname());
    }

    @Test
    void updateUserNickname_UserNotFound() {
        assertThrows(UserNotFoundException.class, () -> {
            authService.updateUserNickname(UUID.randomUUID(), "changeNickname");
        });
    }

    @Test
    void updateUserPassword_Success() {
        authService.updateUserPassword(user.getId(), "changePassword");
        Optional<User> byId = userRepository.findById(user.getId());
        assertTrue(passwordEncoder.matches("changePassword", byId.get().getPassword()));
    }

    @Test
    void updateUserPassword_Success_UserNotFound() {
        assertThrows(UserNotFoundException.class, () -> {
            authService.updateUserPassword(UUID.randomUUID(), "changePassword");
        });
    }

    @Test
    void deleteUserById_Success() {
        authService.deleteUserById(user.getId());
        assertFalse(userRepository.findById(user.getId()).isPresent());
    }

    @Test
    void deleteUserById_Fail() {
        assertThrows(UserNotFoundException.class, () -> {
            authService.deleteUserById(UUID.randomUUID());
        });

    }
}