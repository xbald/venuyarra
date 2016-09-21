package com.venuyarra.aqa;

import com.venuyarra.aqa.dto.TestCase;

import java.util.List;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public interface TestCaseDao {
    TestCase get(Long testCaseId);
    List<TestCase> getAllByTestSuiteId(Long testSuiteId);
}
