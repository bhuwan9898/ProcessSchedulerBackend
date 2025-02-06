package com.wedevelop.ProcessScheduler.controller;


import com.wedevelop.ProcessScheduler.model.AlgorithmType;
import com.wedevelop.ProcessScheduler.model.Procedure;
import com.wedevelop.ProcessScheduler.service.ProcessSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AlgorithmsController {
    @Autowired
    ProcessSchedulerService schedulerService;
    @GetMapping("/")
    public String greet(){
       return "Hello welcome to the scheduling algorithms program";
    }
    @PostMapping("/schedule")
    public ResponseEntity<List<Procedure>> scheduleProcesses(
            @RequestBody List<Procedure> procedures,
            @RequestParam AlgorithmType type,
            @RequestParam int timeQuantum
            ) {
        return ResponseEntity.ok(schedulerService.scheduleProcesses(procedures, type, timeQuantum));
    }


}


