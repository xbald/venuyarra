package com.venuyarra.aqa.services.impl;

import com.venuyarra.aqa.TestCaseDao;
import com.venuyarra.aqa.dto.SeleniumCommand;
import com.venuyarra.aqa.dto.TestCase;
import com.venuyarra.aqa.services.SeleniumCommandService;
import com.venuyarra.aqa.services.TestCaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

    private TestCaseDao testCaseDao;

    private SeleniumCommandService seleniumCommandService;

    @Override
    public TestCase get(Long testCaseId) {
        final TestCase testCase = testCaseDao.get(testCaseId);
        final List<SeleniumCommand> commandList = seleniumCommandService.getAllByTestCaseId(testCaseId);
        testCase.setCommandList(commandList);
        return testCase;
    }

    @Override
    public List<TestCase> getAllBySuiteId(Long testSuiteId) {
        final List<TestCase> testCaseList = testCaseDao.getAllByTestSuiteId(testSuiteId);
        for (TestCase testCase : testCaseList) {
            final List<SeleniumCommand> commandList = seleniumCommandService.getAllByTestCaseId(testCase.getId());
            testCase.setCommandList(commandList);
        }
        return testCaseList;
    }

    public void setTestCaseDao(TestCaseDao testCaseDao) {
        this.testCaseDao = testCaseDao;
    }

    public void setSeleniumCommandService(SeleniumCommandService seleniumCommandService) {
        this.seleniumCommandService = seleniumCommandService;
    }
}
