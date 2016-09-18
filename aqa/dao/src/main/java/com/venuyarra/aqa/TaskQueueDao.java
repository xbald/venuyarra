package com.venuyarra.aqa;

import com.venuyarra.aqa.dto.BrowserType;
import com.venuyarra.aqa.dto.TestTask;

import java.util.List;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public interface TaskQueueDao {
    List<TestTask> getAll();
    BrowserType getBrowserType(Long taskId);
    String getClientId(Long taskId);
}
