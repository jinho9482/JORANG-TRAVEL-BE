package com.example.travel_diary.controller;

import com.example.travel_diary.global.request.SignInRequestDto;
import com.example.travel_diary.global.request.SignUpRequestDto;
import com.example.travel_diary.global.response.GetUserByIdResponseDto;
import com.example.travel_diary.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign_up")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        authService.signUp(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료");
    }
        
    @PostMapping("/sign_in")
    public ResponseEntity<String> signIn(@RequestBody SignInRequestDto signInRequestDto){
        authService.signIn(signInRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 완료");
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserByIdResponseDto> getUserById(@PathVariable UUID id) {
        GetUserByIdResponseDto userById = authService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserNickname(@PathVariable UUID id,@RequestParam String nickname){
        authService.updateUserNickname(nickname);
        return ResponseEntity.status(HttpStatus.OK).body("닉네임 변경 완료");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserPassword(@PathVariable UUID id,@RequestParam String password){
        authService.updateUserPassword(password);
        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경 완료");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable UUID id){
        authService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("탈퇴 완료");
    }

//    @PostMapping("/findLoginId")
//    public ResponseEntity<String> findLoginId(@RequestBody String email){
//        authService.findLoginId(email);
//        return ResponseEntity.status(HttpStatus.OK).body("이메일 전송 완료");
//    }
//
//    @PostMapping("/findPassword")
//    public ResponseEntity<String> findPassword(@RequestBody String loginId){
//        authService.findPassword(loginId);
//        return ResponseEntity.status(HttpStatus.OK).body("이메일 전송 완료");
//    }

}
