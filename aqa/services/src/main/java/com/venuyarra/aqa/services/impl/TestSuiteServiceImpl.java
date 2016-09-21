package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.TestSuiteDao;
import com.venuyarra.aqa.dto.TestCase;
import com.venuyarra.aqa.dto.TestSuite;
import com.venuyarra.aqa.services.TestCaseService;
import com.venuyarra.aqa.services.TestSuiteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
@Transactional
public class TestSuiteServiceImpl implements TestSuiteService {

    private TestSuiteDao testSuiteDao;
    private TestCaseService testCaseService;

    @Override
    public TestSuite get(Long testSuiteId) {
        final TestSuite testSuite = testSuiteDao.getTestSuiteById(testSuiteId);
        final List<TestCase> testCaseList = testCaseService.getAllBySuiteId(testSuiteId);
        testSuite.setTestCaseList(testCaseList);
        return testSuite;
    }

    public void setTestSuiteDao(TestSuiteDao testSuiteDao) {
        this.testSuiteDao = testSuiteDao;
    }

    public void setTestCaseService(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }
}
