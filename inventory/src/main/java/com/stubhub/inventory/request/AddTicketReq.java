package com.stubhub.inventory.request;

import java.math.BigDecimal;

public class AddTicketReq {

    private String ticketName;

    private BigDecimal price;

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
