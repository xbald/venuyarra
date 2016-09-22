package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.dto.TestTask;
import com.venuyarra.aqa.services.TaskQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
@Transactional
public class TaskQueueServiceImpl extends JdbcDaoSupport implements TaskQueueService {
    @Autowired
    private TaskQueueDao taskQueueDao;


    @Override
    public List<TestTask> getAll() {
        return taskQueueDao.getAll();
    }
}
