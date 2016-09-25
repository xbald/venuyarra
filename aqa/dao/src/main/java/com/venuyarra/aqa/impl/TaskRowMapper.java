package com.venuyarra.aqa.impl;

import com.venuyarra.aqa.dto.BrowserType;
import com.venuyarra.aqa.dto.TestTask;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by NIKOLAI on 23.09.2016.
 */
public class TaskRowMapper implements RowMapper<TestTask> {
    @Override
    public TestTask mapRow(ResultSet rs, int rowNum) throws SQLException {
        final TestTask testTask = new TestTask();

        testTask.setId(rs.getLong("id"));
        testTask.setBrowserType(
                BrowserType.valueOf(
                        rs.getString("browser_type").toUpperCase(Locale.ENGLISH)
                )
        );
        testTask.setClientId(rs.getString("client_id"));
        testTask.setStatus(rs.getString("status"));
        testTask.setCreationDate(rs.getDate("date_created"));
        testTask.setFinishDate(rs.getDate("finished_date"));
        testTask.setTestSuiteId(rs.getLong("cts_key"));
        testTask.setUrl(rs.getString("url"));

        return testTask;
    }
}
