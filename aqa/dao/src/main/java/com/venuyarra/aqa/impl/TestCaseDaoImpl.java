package com.venuyarra.aqa.impl;

import com.venuyarra.aqa.SeleniumCommandDao;
import com.venuyarra.aqa.TestCaseDao;
import com.venuyarra.aqa.dto.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public class TestCaseDaoImpl extends JdbcDaoSupport implements TestCaseDao {
    private static final String TABLE_NAME = "cmatrix_testcase_mapping";

    private final String GET_QUERY = "select * from " + TABLE_NAME + " where tsm_key=?";
    private final String GET_BY_SUITE_ID = "select * from " + TABLE_NAME + " where tsm_key in " +
            "(select tsp_key from cmatrix_test_suite_mapping where cts_key=?)";

    private final static TestCaseRowMapper MAPPER = new TestCaseRowMapper();

    @Override
    public TestCase get(Long testCaseId) {
        return getJdbcTemplate()
                .queryForObject(GET_QUERY, TestCase.class);
    }

    @Override
    public List<TestCase> getAllByTestSuiteId(Long testSuiteId) {
        return getJdbcTemplate().query(GET_BY_SUITE_ID, MAPPER, testSuiteId);
    }

    public static class TestCaseRowMapper implements RowMapper<TestCase> {

        @Override
        public TestCase mapRow(ResultSet rs, int rowNum) throws SQLException {
            TestCase testCase = new TestCase();
            testCase.setTitle(rs.getString("tsm_name"));
            testCase.setId(rs.getLong("tsm_key"));
            return testCase;
        }
    }
}