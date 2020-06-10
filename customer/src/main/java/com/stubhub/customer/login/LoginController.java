package com.stubhub.customer.login;

import com.stubhub.common.Response;
import com.stubhub.customer.login.entity.UserEntity;
import com.stubhub.customer.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/customer/login")
    public Response login(String username, String pwd) {

        UserEntity user = loginService.login(username, pwd);

        return Response.success(user, "User login successfully!");
    }

}
