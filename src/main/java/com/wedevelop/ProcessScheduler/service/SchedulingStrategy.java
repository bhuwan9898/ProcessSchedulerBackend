package com.wedevelop.ProcessScheduler.service;

import com.wedevelop.ProcessScheduler.model.Procedure;

import java.util.List;

public interface SchedulingStrategy {
    List<Procedure> schedule(List<Procedure> procedures);
}
