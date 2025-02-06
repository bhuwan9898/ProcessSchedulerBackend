package com.wedevelop.ProcessScheduler.service.procedures;

import com.wedevelop.ProcessScheduler.model.Procedure;
import com.wedevelop.ProcessScheduler.service.SchedulingStrategy;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class FCFS implements SchedulingStrategy {
    @Override
    public List<Procedure> schedule(List<Procedure> procedures) {
        // sort the list first based on first arrival first

        // we need to create a method in all the scheduling strategies that will create start,end, turn around and waiting time.
        // this avoids the DRY principle.
         procedures.sort(Comparator.comparingInt(p -> p.arrivalTime));
         procedures.get(0).startTime = procedures.get(0).arrivalTime;
         procedures.get(0).endTime = procedures.get(0).startTime + procedures.get(0).burstTime;
         for (int i = 1;i<procedures.size();i++){
             Procedure currentProcedure = procedures.get(i);
             // Ensures the CPU does not remain idle if the next process arrives later
             currentProcedure.startTime = Math.max(procedures.get(i - 1).endTime, currentProcedure.arrivalTime);
             currentProcedure.endTime = currentProcedure.startTime + currentProcedure.burstTime;
         }
        System.out.println("FCFS");
        return procedures;
    }
}
