package com.machinecoding.bookmyshow.Models;

import java.util.Date;

public class Show extends BaseModel{
    private Auditorium auditorium;
    private Date startTime;
    private Date endTime;
    private Movie movie;
    private Language language;
}
