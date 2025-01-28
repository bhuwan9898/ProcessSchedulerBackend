package com.wedevelop.ProcessScheduler.factory;

import com.wedevelop.ProcessScheduler.model.AlgorithmType;
import com.wedevelop.ProcessScheduler.service.SchedulingStrategy;
import com.wedevelop.ProcessScheduler.service.procedures.FCFS;
import com.wedevelop.ProcessScheduler.service.procedures.SJF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SchedulingStrategyFactory {

    //each scheduling strategy is kept in the factory by spring as beans
    //strategy list is the list of all of those classes that we have created by
    //implementing SchedulingStrategy interface
    private final Map<AlgorithmType, SchedulingStrategy> strategies;

    @Autowired
    public SchedulingStrategyFactory(List<SchedulingStrategy> strategyList) {
        strategies = new HashMap<>();
        strategies.put(AlgorithmType.FCFS, findStrategy(strategyList, FCFS.class));
        strategies.put(AlgorithmType.SJF, findStrategy(strategyList, SJF.class));
        // Add other strategies
    }

    // this method helps to get the strategy from the list of strategies class by passing the type
    // which are stored in the hash map we created manually
    public SchedulingStrategy getStrategy(AlgorithmType type) {
        return strategies.get(type);
    }

    // this method finds the exact bean that has been stored by spring
    // and returns the exact scheduling strategy that we want
    private SchedulingStrategy findStrategy(List<SchedulingStrategy> strategies, Class<?> strategyClass) {
        return strategies.stream()
                .filter(strategy -> strategy.getClass().equals(strategyClass))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Strategy not found: " + strategyClass));
    }
}
