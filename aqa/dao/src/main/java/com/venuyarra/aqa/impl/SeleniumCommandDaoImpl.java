package com.venuyarra.aqa.impl;

import com.venuyarra.aqa.SeleniumCommandDao;
import com.venuyarra.aqa.dto.SeleniumCommand;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by NIKOLAI on 18.09.2016.
 */
public class SeleniumCommandDaoImpl extends JdbcDaoSupport implements SeleniumCommandDao {




    public static class CommandRowMapper implements RowMapper<SeleniumCommand> {

        @Override
        public SeleniumCommand mapRow(ResultSet resultSet, int i) throws SQLException {
            return new SeleniumCommand();
        }
    }
}
