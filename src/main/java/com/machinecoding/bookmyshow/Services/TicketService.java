package com.machinecoding.bookmyshow.Services;

import com.machinecoding.bookmyshow.Exceptions.InvalidArgumentException;
import com.machinecoding.bookmyshow.Exceptions.SeatNotAvailableException;
import com.machinecoding.bookmyshow.Models.*;
import com.machinecoding.bookmyshow.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;


    @Autowired
    public TicketService(SeatRepository seatRepository, ShowSeatRepository showSeatRepository, ShowRepository showRepository, UserRepository userRepository, TicketRepository ticketRepository){
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket bookTicket(List<Long> seatIds, Long userId, Long showId) throws InvalidArgumentException, SeatNotAvailableException {

        List<Chair> chairs = seatRepository.findAllByIdIn(seatIds);

        Optional<Event> showOptional = showRepository.findById(showId);

        if(showOptional.isEmpty()){
            throw new InvalidArgumentException("Show by: " + showId + " does not exist!");
        }

        Event show = showOptional.get();


        List<ShowSeat> showSeats = getAndLockShowSeats(chairs, show);

        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("User by: " + userId + " does not exist!");
        }

        User user = optionalUser.get();

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setStatus(TicketStatus.PROCESSING);
        ticket.setShow(show);
        ticket.setSeats(chairs);
        ticket.setAmount(0);
        ticket.setTimeOfBooking(new Date());

        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
    public List<ShowSeat> getAndLockShowSeats(List<Chair> chairs, Event show) throws SeatNotAvailableException {
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow(chairs, show);

        for(ShowSeat showSeat: showSeats){
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED)))){
                throw new SeatNotAvailableException();
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();

        for(ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }
        return showSeats;
    }
}
