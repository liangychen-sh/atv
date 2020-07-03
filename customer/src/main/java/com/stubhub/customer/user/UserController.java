package com.stubhub.customer.user;

import com.stubhub.common.Response;
import com.stubhub.customer.user.entity.UserEntity;
import com.stubhub.customer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/user/{id}/profile")
    public Response<UserEntity> userProfile(@PathVariable Long id) {
        return Response.success(userService.findUserById(id), "Find user profile successfully");
    }

    @PostMapping(value = "/user/login")
    public Response<UserEntity> login(String username, String pwd) {

        UserEntity user = userService.login(username, pwd);

        return Response.success(user, "User login successfully!");
    }

    @PostMapping(value = "/user/logout")
    public Response logout(String username) {
        return Response.success(null, "User logout successfully!");
    }


    @PostMapping(value = "/user/tickets")
    public Response<List<Ticket>> listMyTickets(@RequestParam Long userId) {

        return Response.success(userService.listMyTickets(userId), "list tickets successfully");
    }

    @PostMapping(value = "/user/tickets/add")
    public Response addTicket(@RequestBody AddTicketReq addTicketReq) {

        userService.addTicket(addTicketReq);
        return Response.success(null, "add tickets successfully");
    }

    @PostMapping(value = "/user/tickets/add/failed")
    public Response addTicketFailed(@RequestBody AddTicketReq addTicketReq) {

        userService.addTicketFailed(addTicketReq);
        return Response.success(null, "add tickets successfully");
    }

    @GetMapping(value = "/user/slow")
    public Response slowRequest() throws InterruptedException {
        Thread.sleep(2000);
        return Response.success(null, "slow request");
    }


}
