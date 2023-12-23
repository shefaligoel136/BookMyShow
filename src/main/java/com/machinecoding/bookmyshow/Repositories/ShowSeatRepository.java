package com.machinecoding.bookmyshow.Repositories;

import com.machinecoding.bookmyshow.Models.Chair;
import com.machinecoding.bookmyshow.Models.Event;
import com.machinecoding.bookmyshow.Models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) // for update
    List<ShowSeat> findAllBySeatInAndShow(List<Chair> seats, Event event);
    ShowSeat save(ShowSeat showSeat);

}
