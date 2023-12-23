package com.machinecoding.bookmyshow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Chair extends BaseModel{
    private int seatNumber;
    private int rowz;
    private int colz;

    @ManyToOne
    private SeatType seatType;
}
