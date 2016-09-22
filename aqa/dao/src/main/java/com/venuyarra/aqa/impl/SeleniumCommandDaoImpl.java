package com.venuyarra.aqa.impl;

import com.venuyarra.aqa.SeleniumCommandDao;
import com.venuyarra.aqa.dto.ClickCommand;
import com.venuyarra.aqa.dto.EnterCommand;
import com.venuyarra.aqa.dto.SelectCommand;
import com.venuyarra.aqa.dto.SeleniumCommand;
import com.venuyarra.aqa.dto.ValidationCommand;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public class SeleniumCommandDaoImpl extends JdbcDaoSupport implements SeleniumCommandDao {
    private static CommandRowMapper MAPPER = new CommandRowMapper();
    private static String TEST_CASE_TABLE_NAME = "cmatrix_testcase";
    private static String OBJ_MAP_TABLE_NAME = "cmatrix_object_map";

    private final String GET_BY_TEST_CASE_ID =
            "select * from "
                    + TEST_CASE_TABLE_NAME
                    + " as tc where tc.tcs_tsm_key=?";

    private final String GET_BY_COMMAND_ID =
            "select * from "
                    + TEST_CASE_TABLE_NAME
                    + " as tc where tc.tcs_key= ?";

    private final String GET_PARAMETERS = "select * from " + OBJ_MAP_TABLE_NAME + " as om where om.obj_key =?";

    @Override
    public List<SeleniumCommand> getByTestCaseId(Long testCaseId) {
        return getJdbcTemplate().query(GET_BY_TEST_CASE_ID, MAPPER, testCaseId);
    }

    @Override
    public SeleniumCommand get(Long id) {
        return getJdbcTemplate().queryForObject(GET_BY_COMMAND_ID, MAPPER, id);
    }


    public static class CommandRowMapper implements RowMapper<SeleniumCommand> {
        @Override
        public SeleniumCommand mapRow(ResultSet resultSet, int i) throws SQLException {
            final String tcs_action = resultSet.getString("tcs_action");
            final Long obj_key = resultSet.getLong("tcs_obj_key");
            final Long id = resultSet.getLong("tcs_key");
            switch (tcs_action) {
                case "Click": {
                    ClickCommand clickCommand = new ClickCommand();
                    clickCommand.setId(id);
                    clickCommand.setParameter(obj_key);
                    return clickCommand;
                }
                case "Validation": {
                    ValidationCommand validationCommand = new ValidationCommand();
                    validationCommand.setId(id);
                    validationCommand.setExpectedResult(resultSet.getString("tcs_expected_result"));
                    validationCommand.setParameter(obj_key);
                    return validationCommand;
                }
                case "Select": {
                    SelectCommand selectCommand = new SelectCommand();
                    selectCommand.setId(id);
                    selectCommand.setValue(resultSet.getString("tcs_obj_val"));
                    selectCommand.setParameter(obj_key);
                    return selectCommand;
                }

                case "Enter": {
                    EnterCommand enterCommand = new EnterCommand();
                    enterCommand.setId(id);
                    enterCommand.setValue(resultSet.getString("tcs_obj_val"));
                    enterCommand.setParameter(obj_key);
                    return enterCommand;
                }

                default:
                    throw new IllegalArgumentException("'" + tcs_action + "' not supported command");
            }
        }
    }


}
