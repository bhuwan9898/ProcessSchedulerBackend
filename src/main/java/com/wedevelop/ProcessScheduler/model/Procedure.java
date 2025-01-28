package com.wedevelop.ProcessScheduler.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Procedure {
    public int procedureId;
    public int arrivalTime;
    public int burstTime;
}


