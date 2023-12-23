package com.machinecoding.bookmyshow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{

    @ManyToOne
    private Event show;

    @ManyToOne
    private Chair seat;
    private ShowSeatStatus showSeatStatus;
    private Date lockedAt;
}
