package com.stubub.ticket.userticket;

import com.stubhub.common.Response;
import com.stubub.ticket.userticket.entity.TicketEntity;
import com.stubub.ticket.userticket.request.AddTicketReq;
import com.stubub.ticket.userticket.service.UserTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserTicketController {

    @Autowired
    private UserTicketService userTicketService;

    @GetMapping("tickets")
    public Response<List<TicketEntity>> listTickets(@RequestParam Long userId) {

        return Response.success(userTicketService.listUserTickets(userId), "list ticket successfully");
    }

}
