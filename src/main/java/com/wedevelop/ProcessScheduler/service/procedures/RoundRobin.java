package com.wedevelop.ProcessScheduler.service.procedures;

import com.wedevelop.ProcessScheduler.model.Procedure;
import com.wedevelop.ProcessScheduler.service.SchedulingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class RoundRobin implements SchedulingStrategy {
    @Override
    public List<Procedure> schedule(List<Procedure> procedures, int timeQuantum) {
        System.out.println("RoundRobin");
        List<Procedure> result = new ArrayList<>();
        procedures.sort(Comparator.comparingInt(p -> p.arrivalTime));

        Queue<Procedure> remainingProcedures = new LinkedList<>(procedures);
        int currentTime = procedures.get(0).arrivalTime;

        while (!remainingProcedures.isEmpty()) {
            Procedure runningProcedure = remainingProcedures.poll();

            // Set start time for the current execution
            runningProcedure.startTime = currentTime;

            if (runningProcedure.burstTime <= timeQuantum) {
                // Process completes execution
                currentTime += runningProcedure.burstTime;
                runningProcedure.endTime = currentTime;
                result.add(runningProcedure);
            } else {
                // Process needs more time, run for timeQuantum and create a copy
                currentTime += timeQuantum;
                runningProcedure.endTime = currentTime;

                // Add execution record to result
                result.add(runningProcedure);

                // Create a copy of the process with reduced burst time
                Procedure newCopy = new Procedure(runningProcedure.procedureName,runningProcedure.arrivalTime, runningProcedure.burstTime - timeQuantum);
                remainingProcedures.add(newCopy);
            }
        }

        return result;
    }

}
