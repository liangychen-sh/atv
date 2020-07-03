package com.stubub.ticket.userticket.repository;

import com.stubub.ticket.userticket.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<TicketEntity, Long> {

    List<TicketEntity> findByUserId(Long userId);
}
