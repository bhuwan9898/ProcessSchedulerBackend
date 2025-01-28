package com.wedevelop.ProcessScheduler.service.procedures;

import com.wedevelop.ProcessScheduler.model.Procedure;
import com.wedevelop.ProcessScheduler.service.SchedulingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FCFS implements SchedulingStrategy {
    @Override
    public List<Procedure> schedule(List<Procedure> procedures) {
        // FCFS implementation
        System.out.println("FCFS");
        System.out.println(procedures.stream().toList());
        return procedures.stream().toList();
    }
}
