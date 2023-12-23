package com.machinecoding.bookmyshow.Controllers;

import com.machinecoding.bookmyshow.Dto.BookTicketRequestDTO;
import com.machinecoding.bookmyshow.Dto.BookTicketResponseDTO;
import com.machinecoding.bookmyshow.Services.TicketService;
import org.springframework.stereotype.Controller;

import java.util.concurrent.TimeoutException;

@Controller
public class TicketController {

    private TicketService ticketService;

    TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }
    public BookTicketResponseDTO bookTicket(BookTicketRequestDTO bookTicketRequestDTO){
        BookTicketResponseDTO responseDTO = new BookTicketResponseDTO();

//        try{
//            ticketService.bookTicket();
//        }catch (TimeoutException e){
//            responseDTO.setStatus("FAILURE");
//            responseDTO.setMessage("Something went wrong");
//        }

        return null;
    }
}
