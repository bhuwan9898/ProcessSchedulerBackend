package com.wedevelop.ProcessScheduler.service.procedures;

import com.wedevelop.ProcessScheduler.model.Procedure;
import com.wedevelop.ProcessScheduler.service.SchedulingStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SJF implements SchedulingStrategy {
    @Override
    public List<Procedure> schedule(List<Procedure> procedures, int timeQuantum) {
        // SJF implementation

        // list to store the result
        List<Procedure> result = new ArrayList<>();

        // first sort the list based on least arrival time first
        procedures.sort(Comparator.comparingInt(p -> p.arrivalTime));

        // initialize current time to the arrival time of first one
        int currentTime = procedures.get(0).arrivalTime;

        // create a copy of procedures to track unprocessed ones
        List<Procedure> remainingProcedures = new ArrayList<>(procedures);

        while (!remainingProcedures.isEmpty()) {
            // creating local variable to use inside lambda expression
            int finalCurrentTime = currentTime;
            // get processes that have arrived by current time

            List<Procedure> availableProcedures = remainingProcedures.stream()
                    .filter(p -> p.arrivalTime <= finalCurrentTime)
                    .sorted(Comparator.comparingInt(p -> p.burstTime)) // SJF: Pick shortest job
                    .toList();

            if (availableProcedures.isEmpty()) {
                // if no process is available, move time to next process arrival
                currentTime = remainingProcedures.get(0).arrivalTime;
                continue;
            }

            // pick the shortest job since it is already sorted based on least burst time first
            Procedure runningProcedure = availableProcedures.get(0);
            runningProcedure.startTime = currentTime;
            runningProcedure.endTime = runningProcedure.startTime + runningProcedure.burstTime;
            result.add(runningProcedure);

            // Update current time to the end time of the running process
            currentTime = runningProcedure.endTime;

            // Remove processed procedure from remaining list
            remainingProcedures.remove(runningProcedure);
        }
        return result;
    }
}
