package com.venuyarra.aqa.impl;

import com.venuyarra.aqa.TaskQueueDao;
import com.venuyarra.aqa.dto.BrowserType;
import com.venuyarra.aqa.dto.TestTask;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public class TaskQueueDaoImpl extends JdbcDaoSupport implements TaskQueueDao {
    private static final String TASK_TABLE = "TBL_PRODUCT";
    private static final String GET_ALL_QUERY = "select * from " + TASK_TABLE + " order by client_id";
    private static final String GET_CLIENT_ID_QUERY = "select client_id from " + TASK_TABLE + " where id = ?";
    private static final String GET_COUNT_QUERY = "select count from  " + TASK_TABLE + " where id = ?";
    private static final String GET_BROWSER_TYPE_QUERY = "select browser_type from " + TASK_TABLE + " where id = ?";
    private static final String UPDATE_COUNT_QUERY = "update " + TASK_TABLE + " set count = ? where id = ?";

    private static TaskRowMapper MAPPER = new TaskRowMapper();


    @Override
    public List<TestTask> getAll() {
        return getJdbcTemplate().query(GET_ALL_QUERY, MAPPER);
    }

    @Override
    public BrowserType getBrowserType(Long taskId) {
        return BrowserType.valueOf(
                getJdbcTemplate().queryForObject(GET_BROWSER_TYPE_QUERY, String.class, taskId)
        );
    }

    @Override
    public String getClientId(Long taskId) {
        return getJdbcTemplate().queryForObject(GET_CLIENT_ID_QUERY, String.class, taskId);
    }

    private static class TaskRowMapper implements RowMapper<TestTask> {
        @Override
        public TestTask mapRow(ResultSet resultSet, int i) throws SQLException {
            final TestTask testTask = new TestTask();
            testTask.setId(resultSet.getLong("id"));
            testTask.setBrowserType(BrowserType.valueOf(resultSet.getString("browser_type")));
            testTask.setClientId(resultSet.getString("client_id"));
            return testTask;
        }
    }
}
