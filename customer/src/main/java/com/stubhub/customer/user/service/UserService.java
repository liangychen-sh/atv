package com.stubhub.customer.user.service;

import com.stubhub.customer.user.AddTicketReq;
import com.stubhub.customer.user.Ticket;
import com.stubhub.customer.user.entity.UserEntity;

import java.util.List;

public interface UserService {

    public UserEntity login(String username, String pwd);

    public UserEntity findUserById(Long id);

    public List<Ticket> listMyTickets(Long userId);

    public void addTicket(AddTicketReq addTicketReq);

    public void addTicketFailed(AddTicketReq addTicketReq);
}
