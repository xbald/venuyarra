package com.venuyarra.aqa.impl;

import com.venuyarra.aqa.TestSuiteDao;
import com.venuyarra.aqa.dto.TestSuite;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public class TestSuiteDaoImpl extends JdbcDaoSupport implements TestSuiteDao {

    private final static String GET_BY_ID =
            "select * from cmatrix_test_suite_mapping as tsm join cmatrix_test_suite as ts on tsm.cts_key=ts.tts_key where ts_key=?";

    private final static TestSuiteRowMapper MAPPER = new TestSuiteRowMapper();

    @Override
    public TestSuite getTestSuiteById(Long testSuiteId) {
        return getJdbcTemplate().queryForObject(GET_BY_ID, MAPPER, testSuiteId);
    }

    public static class TestSuiteRowMapper implements RowMapper<TestSuite> {
        @Override
        public TestSuite mapRow(ResultSet rs, int rowNum) throws SQLException {
            TestSuite testSuite = new TestSuite();
            testSuite.setId(rs.getLong("ts_key"));
            testSuite.setTitle(rs.getString("tts_name"));
            return testSuite;
        }
    }
}
