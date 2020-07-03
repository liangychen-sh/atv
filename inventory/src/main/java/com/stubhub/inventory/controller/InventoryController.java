package com.stubhub.inventory.controller;

import com.stubhub.common.Response;
import com.stubhub.inventory.request.AddTicketReq;
import com.stubhub.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/inventory/add")
    public Response addTicket(@RequestBody AddTicketReq addTicketReq) {

        inventoryService.addTicket(addTicketReq);
        return Response.success(null, "Add Ticket successfully");
    }

    @PostMapping("/inventory/add/failed")
    public Response addTicketFailed(@RequestBody AddTicketReq addTicketReq) {
        throw new RuntimeException("add ticket failed");
    }

}
