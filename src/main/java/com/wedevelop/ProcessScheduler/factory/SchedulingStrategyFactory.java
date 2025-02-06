package com.wedevelop.ProcessScheduler.factory;

import com.wedevelop.ProcessScheduler.model.AlgorithmType;
import com.wedevelop.ProcessScheduler.service.SchedulingStrategy;
import com.wedevelop.ProcessScheduler.service.procedures.FCFS;
import com.wedevelop.ProcessScheduler.service.procedures.LJF;
import com.wedevelop.ProcessScheduler.service.procedures.RoundRobin;
import com.wedevelop.ProcessScheduler.service.procedures.SJF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SchedulingStrategyFactory {
    private final Map<AlgorithmType, SchedulingStrategy> strategies;

    @Autowired
    public SchedulingStrategyFactory(List<SchedulingStrategy> strategyList) {
        strategies = new HashMap<>();
        strategies.put(AlgorithmType.FCFS, findStrategy(strategyList, FCFS.class));
        strategies.put(AlgorithmType.SJF, findStrategy(strategyList, SJF.class));
        strategies.put(AlgorithmType.LJF, findStrategy(strategyList, LJF.class));
        strategies.put(AlgorithmType.ROUND_ROBIN, findStrategy(strategyList, RoundRobin.class));
    }

    private SchedulingStrategy findStrategy(List<SchedulingStrategy> strategies, Class<?> strategyClass) {
        return strategies.stream()
                .filter(strategy -> strategy.getClass().equals(strategyClass))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Strategy not found: " + strategyClass));
    }

    public SchedulingStrategy getStrategy(AlgorithmType type) {
        return strategies.get(type);
    }
}
