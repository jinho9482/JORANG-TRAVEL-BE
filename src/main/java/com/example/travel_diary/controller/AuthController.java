package com.example.travel_diary.controller;

import com.example.travel_diary.global.request.*;
import com.example.travel_diary.global.response.GetUserByIdResponseDto;
import com.example.travel_diary.global.response.LoginInResponseDto;
import com.example.travel_diary.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
//    @Operation(summary = "회원가입", description = "회원가입")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) throws Exception {
        UUID uuid = authService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료"+uuid);
    }

    @PostMapping("/signIn")
//    @Operation(summary = "로그인", description = "로그인")
    public LoginInResponseDto signIn(@RequestBody SignInRequest signInRequest) throws Exception {
        return authService.signIn(signInRequest);
//        return ResponseEntity.status(HttpStatus.OK).body("로그인 완료");
    }

//    회원가입할 때 이미 존재하는 이메일인지 확인
    @GetMapping("/email/{email}")
    public String possibleUserByEmail(@PathVariable(name = "email") String email) throws Exception {
        return authService.possibleUserByEmail(email);
    }
    //    회원가입할 때 이미 존재하는 로그인 아이디인지 확인

    @GetMapping("/loginId/{loginId}")
    public String possibleUserByLoginId(@PathVariable(name = "loginId") String loginId) throws Exception {
        return authService.possibleUserByLoginId(loginId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetUserByIdResponseDto> getUserById(@PathVariable(name = "id") UUID id) throws Exception {
        GetUserByIdResponseDto userById = authService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userById);
    }

    //    http://localhost:8080/api/v1/auths/318ef9bd-7c16-43f8-8149-6660c93c41c3?type=nickname
//    body : nickname1change
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "id") UUID id,
                                             @RequestBody UpdateUserRequest req,
                                             @RequestParam(value= "type") String type
                                             ) throws Exception {
        if ("nickname".equals(type)) {
            authService.updateUserNickname(id, req.value());
            System.out.println("!!!!!!!!!!!!!!"+req.value());
            return ResponseEntity.status(HttpStatus.OK).body("닉네임 변경 완료");
        } else if ("password".equals(type)) {
            authService.updateUserPassword(id,req.value());
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경 완료");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("올바른 요청이 아닙니다.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable(name = "id") UUID id) throws Exception {
        authService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("탈퇴 완료");
    }

    @PostMapping("/findLoginId")
    public ResponseEntity<String> findLoginId(@RequestBody FindLoginIdRequest findLoginIdRequest) throws Exception {
        authService.findLoginId(findLoginIdRequest);
        return ResponseEntity.status(HttpStatus.OK).body("아이디 찾기 이메일 전송 완료");
    }

    @PostMapping("/findPassword")
    public ResponseEntity<String> findPassword(@RequestBody FindPasswordRequest findPasswordRequest) throws Exception {
        authService.findPassword(findPasswordRequest);
        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 찾기 이메일 전송 완료");
    }

}
