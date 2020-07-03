package com.stubhub.inventory.service;

import com.stubhub.inventory.entity.InventoryEntity;
import com.stubhub.inventory.repository.InventoryRepository;
import com.stubhub.inventory.request.AddTicketReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository repository;

    public void addTicket(AddTicketReq addTicketReq) {

        InventoryEntity inventory = repository.findByTicketName(addTicketReq.getTicketName());
        if (inventory == null) {
            inventory = new InventoryEntity();
            inventory.setCount(1L);
            inventory.setPrice(new BigDecimal(100));
            inventory.setTicketName("NBA Finals 2020");
            repository.save(inventory);
            return;
        }

        inventory.setCount(inventory.getCount() == null ? 0 : inventory.getCount() + 1);
        repository.save(inventory);
    }

}
