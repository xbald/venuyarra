package com.venuyarra.aqa;

import com.venuyarra.aqa.dto.TestSuite;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public interface TestSuiteDao {
    TestSuite getTestSuiteById(Long testSuiteId);
}
