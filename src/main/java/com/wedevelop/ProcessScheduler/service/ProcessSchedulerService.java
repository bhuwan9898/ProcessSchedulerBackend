package com.wedevelop.ProcessScheduler.service;

import com.wedevelop.ProcessScheduler.factory.SchedulingStrategyFactory;
import com.wedevelop.ProcessScheduler.model.AlgorithmType;
import com.wedevelop.ProcessScheduler.model.Procedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessSchedulerService {

    @Autowired
    private final SchedulingStrategyFactory strategyFactory;



    public ProcessSchedulerService(SchedulingStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public List<Procedure> scheduleProcesses(List<Procedure> procedures, AlgorithmType type) {
        SchedulingStrategy strategy = strategyFactory.getStrategy(type);
        return strategy.schedule(procedures);
    }
}
