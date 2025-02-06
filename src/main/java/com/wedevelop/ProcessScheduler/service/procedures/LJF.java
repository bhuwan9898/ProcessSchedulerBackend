package com.wedevelop.ProcessScheduler.service.procedures;


import com.wedevelop.ProcessScheduler.model.Procedure;
import com.wedevelop.ProcessScheduler.service.SchedulingStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Longest job first
@Component
public class LJF implements SchedulingStrategy {
    @Override
    public List<Procedure> schedule(List<Procedure> procedures, int timeQuantum){
        //LJF implementation

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
                    .sorted((p1, p2) -> Integer.compare(p2.burstTime, p1.burstTime)) // LJF: Pick longest job from remaining list
                    .toList();

            if (availableProcedures.isEmpty()) {
                // if no process is available, move time to next process arrival
                currentTime = remainingProcedures.get(0).arrivalTime;
                continue;
            }

            // pick the longest job since it is already sorted based on descending order of burst time
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
