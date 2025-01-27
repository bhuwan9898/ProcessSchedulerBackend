package com.wedevelop.ProcessScheduler.service;

import java.util.List;

public interface SchedulingStrategy {
    List<Process> schedule(List<Process> processes);
}
