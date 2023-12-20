package com.machinecoding.bookmyshow.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Ticket extends BaseModel{
    private int amount;
    private Date timeOfBooking;
    private List<Seat> seats;
    private User user;
    private Show show;
    private List<Payment> payments;
    private TicketStatus status;
}
