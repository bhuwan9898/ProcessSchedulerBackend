package com.wedevelop.ProcessScheduler.model;


import lombok.Data;

@Data
public class Process {
    int processId;
    int arrivalTime;
    int burstTime;
    String processName;

}


