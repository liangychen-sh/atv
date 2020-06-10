package com.stubhub.customer.login.service;

import com.stubhub.customer.login.entity.UserEntity;
import com.stubhub.customer.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity login(String username, String pwd) {

        UserEntity user = userRepository.findByUsername(username);

        if (user.getPassword().equals(pwd)) {
            throw new RuntimeException("account or password not incorrect!");
        }

        return user;
    }
}
