package com.example.travel_diary.service;


import com.example.travel_diary.global.request.FindLoginIdRequest;
import com.example.travel_diary.global.request.FindPasswordRequest;
import com.example.travel_diary.global.request.SignInRequest;
import com.example.travel_diary.global.request.SignUpRequest;
import com.example.travel_diary.global.response.GetUserByIdResponseDto;
import com.example.travel_diary.global.response.LoginInResponseDto;

import java.util.UUID;

public interface AuthService {
    UUID signUp(SignUpRequest signUpRequest) throws Exception;
    LoginInResponseDto signIn(SignInRequest signInRequest) throws Exception;

    String possibleUserByEmail(String email) throws Exception;

    String possibleUserByLoginId(String loginId) throws Exception;

    GetUserByIdResponseDto getUserById(UUID id) throws Exception;

    void updateUserNickname(UUID id, String nickname) throws Exception;

    void updateUserPassword(UUID id, String password) throws Exception;

    void deleteUserById(UUID id) throws Exception;

    void findLoginId(FindLoginIdRequest req) throws Exception;
    void findPassword(FindPasswordRequest req) throws Exception;
}
