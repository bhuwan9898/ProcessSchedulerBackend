package com.wedevelop.ProcessScheduler.controller;


import com.wedevelop.ProcessScheduler.model.AlgorithmType;
import com.wedevelop.ProcessScheduler.service.ProcessSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AlgorithmsController {
        private final ProcessSchedulerService schedulerService;

        @Autowired
        public AlgorithmsController(ProcessSchedulerService schedulerService) {
            this.schedulerService = schedulerService;
        }
        @PostMapping("/schedule")
        public ResponseEntity<List<Process>> scheduleProcesses(
                @RequestBody List<Process> processes,
                @RequestParam AlgorithmType type) {
            return ResponseEntity.ok(schedulerService.scheduleProcesses(processes, type));
        }
    }


