package com.example.travel_diary.service;

import com.example.travel_diary.global.request.SignInRequestDto;
import com.example.travel_diary.global.request.SignUpRequestDto;
import com.example.travel_diary.global.response.GetUserByIdResponseDto;

import java.util.UUID;

public class AuthServiceImpl implements AuthService {
    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {

    }

    @Override
    public void signIn(SignInRequestDto signInRequestDto) {

    }

    @Override
    public GetUserByIdResponseDto getUserById(UUID id) {
        return null;
    }

    @Override
    public void updateUserNickname(String nickname) {

    }

    @Override
    public void updateUserPassword(String password) {

    }

    @Override
    public void deleteUserById(UUID id) {

    }

    @Override
    public void findLoginId(String email) {

    }

    @Override
    public void findPassword(String loginId) {

    }
}
