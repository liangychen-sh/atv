package com.stubub.ticket.userticket.service;

import com.stubub.ticket.userticket.entity.TicketEntity;
import com.stubub.ticket.userticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTicketServiceImpl implements UserTicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketEntity> listUserTickets(Long userId) {

        System.out.println(ticketRepository.findByUserId(userId));
        return ticketRepository.findByUserId(userId);
    }
}
