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
         Procedure firstProcedure = procedures.get(0);
         firstProcedure.startTime = firstProcedure.arrivalTime;
         firstProcedure.endTime = firstProcedure.startTime + firstProcedure.burstTime;
         for (int i = 1;i<procedures.size();i++){
             Procedure currentProcedure = procedures.get(i);
             currentProcedure.startTime = procedures.get(i-1).endTime;
             currentProcedure.endTime = currentProcedure.startTime + currentProcedure.burstTime;
         }
        System.out.println("FCFS");
        return procedures;
    }
}
