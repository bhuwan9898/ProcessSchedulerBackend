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
        if (procedures == null || procedures.isEmpty() || timeQuantum <= 0) {
            throw new IllegalArgumentException("Invalid input: procedures list is empty or time quantum is non-positive.");
        }

        System.out.println("RoundRobin Scheduling");
        List<Procedure> result = new ArrayList<>();
        procedures.sort(Comparator.comparingInt(p -> p.arrivalTime));

        Queue<Procedure> readyQueue = new LinkedList<>(procedures);
        int currentTime = procedures.get(0).arrivalTime;

        while (!readyQueue.isEmpty()) {
            Procedure currentProcedure = readyQueue.poll();

            // Ensure currentTime is not behind the procedure's arrival time
            currentTime = Math.max(currentTime, currentProcedure.arrivalTime);

            // Set start time for the current execution
            currentProcedure.startTime = currentTime;

            // Determine the execution time (minimum of burst time and time quantum)
            int executionTime = Math.min(currentProcedure.burstTime, timeQuantum);

            // Update current time
            currentTime += executionTime;

            // Update burst time for the procedure
            currentProcedure.burstTime -= executionTime;

            // Calculate waiting time and turnaround time
            currentProcedure.waitingTime = currentTime - currentProcedure.arrivalTime - executionTime;
            currentProcedure.turnAroundTime = currentProcedure.waitingTime + executionTime;

            // Add the procedure to the result list
            result.add(currentProcedure);

            // If the procedure has remaining burst time, re-add it to the queue
            if (currentProcedure.burstTime > 0) {
                readyQueue.add(currentProcedure);
            }
        }

        return result;
    }

}
