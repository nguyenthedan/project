package com.example.blog.services.impl;

import org.springframework.stereotype.Service;

import com.example.blog.services.LoginService;

import java.util.Objects;

@Service
public class LoginServiceStubImp implements LoginService {

    @Override
    public boolean authentice(String username, String password) {
        return Objects.equals(username, password);
    }
}
