package com.stubhub.inventory.repository;

import com.stubhub.inventory.entity.InventoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<InventoryEntity,Long> {

    InventoryEntity findByTicketName(String ticketName);

}
