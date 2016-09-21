package com.venuyarra.aqa.impl;

import com.venuyarra.aqa.ParameterDao;
import com.venuyarra.aqa.dto.Parameter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by NIKOLAI on 19.09.2016.
 */
public class ParameterDaoImpl extends JdbcDaoSupport implements ParameterDao {
    private final static String QUERY = "select * from cmatrix_object_map as om where op.obj_key=?";
    private static final ParameterMapper MAPPER = new ParameterMapper();

    @Override
    public Parameter get(Long id) {
        return getJdbcTemplate().queryForObject(QUERY, MAPPER, id);
    }

    public static class ParameterMapper implements RowMapper<Parameter> {

        @Override
        public Parameter mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Parameter parameter = new Parameter();
            final String obj_prop1 = rs.getString("obj_prop1");
            final String obj_prop_val1 = rs.getString("obj_prop_val1");

            parameter.setId(rs.getLong("obj_key"));
            parameter.setLocatorType(obj_prop1);
            parameter.setLocatorValue(obj_prop_val1);
            return parameter;
        }
    }
}
