package com.venuyarra.aqa.impl;

import com.venuyarra.aqa.ClientResultDao;
import com.venuyarra.aqa.dto.ClientResponse;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.Arrays;

/**
 * Created by NIKOLAI on 25.09.2016.
 */
public class ClientResultDaoImpl extends JdbcDaoSupport implements ClientResultDao {
    private final static String tableName = "cmatrix_test_results";
    private final static String insertSql = "insert into " + tableName +
            "(trs_tts_key, trs_tcs_key, trs_folder_id, trs_actual_result, trs_result, trs_last_update_date)" +
            " values(?, ?, ?, ?, ?, ?)";
    private final static String updateSql = "update " + tableName +
            " set trs_tts_key=?, trs_tcs_key=?, trs_folder_id=?, trs_actual_result=?, trs_result=?, trs_last_update_date=?" +
            " where trs_id=?";

    private final static String getSql = "select * " + tableName + " where trs_id=?";

    @Override
    public ClientResponse saveOrUpdate(ClientResponse clientResponse) {
        if (clientResponse.getId() != null) {
            return update(clientResponse);
        } else {
            return insert(clientResponse);
        }
    }

    public ClientResponse insert(ClientResponse clientResponse) {
        String returnedValue = clientResponse.getReturnedValue();
        final Throwable throwable = clientResponse.getThrowable();
        if (returnedValue == null && throwable != null) {
            returnedValue = throwable.toString() + "\n" + Arrays.toString(throwable.getStackTrace());
        }
        final int id = getJdbcTemplate().update(
                insertSql,
                clientResponse.getSuiteId(),
                clientResponse.getCommandId(),
                clientResponse.getStarted(),
                returnedValue,
                clientResponse.getExecutionResult().toString(),
                clientResponse.getFinished());
        clientResponse.setId((long) id);
        return clientResponse;
    }

    public ClientResponse update(ClientResponse clientResponse) {
        String returnedValue = clientResponse.getReturnedValue();
        final Throwable throwable = clientResponse.getThrowable();
        if (returnedValue == null && throwable != null) {
            returnedValue = throwable.toString() + "\n" + Arrays.toString(throwable.getStackTrace());
        }

        final int id = getJdbcTemplate().update(
                insertSql,
                clientResponse.getSuiteId(),
                clientResponse.getCommandId(),
                clientResponse.getStarted(),
                returnedValue,
                clientResponse.getExecutionResult().toString(),
                clientResponse.getFinished(),
                clientResponse.getId()
        );
        clientResponse.setId((long) id);
        return clientResponse;
    }
}
