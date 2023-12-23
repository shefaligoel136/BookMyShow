package com.machinecoding.bookmyshow.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketResponseDTO {
    private int amount;
    private Long ticketId;
    private List<String> seatNumber;
    private String auditoriumName;
    private String status;
    private String message;
}
