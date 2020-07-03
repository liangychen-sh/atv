package com.stubub.ticket.userticket.service;

import com.stubub.ticket.userticket.entity.TicketEntity;
import com.stubub.ticket.userticket.request.AddTicketReq;

import java.util.List;

public interface UserTicketService {

    List<TicketEntity> listUserTickets(Long userId);

    void addTicket(AddTicketReq addTicketReq);
}
