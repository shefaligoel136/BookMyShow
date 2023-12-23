package com.machinecoding.bookmyshow.Repositories;

import com.machinecoding.bookmyshow.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long id);
}
