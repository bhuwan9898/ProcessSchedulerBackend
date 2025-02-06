package com.wedevelop.ProcessScheduler.service.procedures;

import com.wedevelop.ProcessScheduler.model.Procedure;
import com.wedevelop.ProcessScheduler.service.SchedulingStrategy;

import java.util.List;

public class RoundRobin implements SchedulingStrategy {
    public List<Procedure> schedule(List<Procedure> procedures, int timeQuantum){
        // remove from the list if it has completed the burst time (burst time = run time - time quantum)
        return null;
    }
}
