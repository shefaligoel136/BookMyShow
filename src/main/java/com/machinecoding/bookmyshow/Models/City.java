package com.machinecoding.bookmyshow.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class City extends BaseModel{
    private List<Theater> theaters;
    private String name;
}
