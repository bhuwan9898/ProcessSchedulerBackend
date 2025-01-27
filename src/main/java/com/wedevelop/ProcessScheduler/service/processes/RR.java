package com.wedevelop.ProcessScheduler.service.processes;

import com.wedevelop.ProcessScheduler.service.SchedulingStrategy;

import java.util.List;

public class RR implements SchedulingStrategy {
    @Override
    public List<Process> schedule(List<Process> processes) {
        // FCFS implementation
        return processes.stream().toList();
    }
}
