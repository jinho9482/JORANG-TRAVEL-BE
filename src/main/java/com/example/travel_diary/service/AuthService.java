package com.example.travel_diary.service;


import com.example.travel_diary.global.request.SignInRequestDto;
import com.example.travel_diary.global.request.SignUpRequestDto;
import com.example.travel_diary.global.response.GetUserByIdResponseDto;

import java.util.UUID;

public interface AuthService {
    void signUp(SignUpRequestDto signUpRequestDto);
    void signIn(SignInRequestDto signInReqestDto);

    GetUserByIdResponseDto getUserById(UUID id);

    void updateUserNickname(String nickname);

    void updateUserPassword(String password);

    void deleteUserById(UUID id);

    void findLoginId(String email);
    void findPassword(String loginId);
}
