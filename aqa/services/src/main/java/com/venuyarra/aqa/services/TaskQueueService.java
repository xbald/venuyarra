package com.venuyarra.aqa.services;

import com.venuyarra.aqa.dto.TestTask;

import java.util.List;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public interface TaskQueueService {
    List<TestTask> getAll();
}
