package com.venuyarra.aqa.selenium;

import com.venuyarra.aqa.dto.TestSuite;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public interface TestSuiteProcessor {
    void processTestSuite(TestSuite testCase, String clientId, String browser, String url);
}

