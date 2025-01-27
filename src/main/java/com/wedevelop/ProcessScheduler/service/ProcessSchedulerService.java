package com.wedevelop.ProcessScheduler.service;

import com.wedevelop.ProcessScheduler.factory.SchedulingStrategyFactory;
import com.wedevelop.ProcessScheduler.model.AlgorithmType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProcessSchedulerService {
    private final SchedulingStrategyFactory strategyFactory;

    @Autowired
    public ProcessSchedulerService(SchedulingStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public List<Process> scheduleProcesses(List<Process> processes, AlgorithmType type) {
        SchedulingStrategy strategy = strategyFactory.getStrategy(type);
        return strategy.schedule(processes);
    }
}
