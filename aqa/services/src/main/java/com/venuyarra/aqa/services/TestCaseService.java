package com.venuyarra.aqa.services;

import com.venuyarra.aqa.dto.TestCase;

import java.util.List;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public interface TestCaseService {
    TestCase get(Long testCaseId);
    List<TestCase> getAllBySuiteId(Long testSuiteId);
}
