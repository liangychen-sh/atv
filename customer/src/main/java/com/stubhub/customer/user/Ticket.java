package com.stubhub.customer.user;

import java.math.BigDecimal;

public class Ticket {

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
