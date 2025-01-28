package com.wedevelop.ProcessScheduler.service.procedures;

import com.wedevelop.ProcessScheduler.model.Procedure;
import com.wedevelop.ProcessScheduler.service.SchedulingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SJF implements SchedulingStrategy {
    @Override
    public List<Procedure> schedule(List<Procedure> procedures) {
        // SJF implementation
        System.out.println("SJF");
        return procedures.stream().toList();
    }
}
