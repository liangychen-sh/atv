package com.stubhub.customer.login.service;

import com.stubhub.customer.login.entity.UserEntity;

public interface LoginService {

    public UserEntity login(String username, String pwd);
}
