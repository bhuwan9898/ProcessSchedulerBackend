package com.wedevelop.ProcessScheduler.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Procedure {
    @JsonProperty("processName")
    public String procedureName;
    public int arrivalTime;
    public int burstTime;
    public int startTime;
    public int endTime;

    public int waitingTime;

    public int turnAroundTime;

    public Procedure(String procedureName, int initialArrivalTime, int newBurstTime) {
        this.arrivalTime = initialArrivalTime;
        this.procedureName = procedureName;
        this.burstTime = newBurstTime;


    }
}


