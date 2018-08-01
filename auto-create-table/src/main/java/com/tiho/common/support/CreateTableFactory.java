package com.tiho.common.support;


import com.tiho.common.entity.CreateTableConfig;
import com.tiho.common.util.ExpressionDateFormatUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by TimoRD on 2017/11/8.
 */
public class CreateTableFactory {

    private JdbcTemplate jdbcTemplate;

    public CreateTableFactory(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(new AbstractDataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                Connection connection = dataSource.getConnection();
                connection.setAutoCommit(true);
                return connection;
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                throw new UnsupportedOperationException();
            }
        });
        this.jdbcTemplate.afterPropertiesSet();
    }

    public CreateTableFactory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable(CreateTableConfig tableConfig) {
        String tableTemplate = tableConfig.getTableTemplate();
        String dateFormat = tableConfig.getDateFormat();

        Date now = new Date();
        Date nextMonth = DateUtils.addMonths(now, +1);
        String monthTable = ExpressionDateFormatUtil.format(tableTemplate, now, dateFormat);
        String nextMonthTable = ExpressionDateFormatUtil.format(tableTemplate, nextMonth, dateFormat);

        boolean hasTable = hasTable(nextMonthTable);
        if (!hasTable) {
            createTable(monthTable, nextMonthTable);
        }
    }

    public void createTable(String monthTable, String nextMonthTable) {
        String createTableSql = String.format("create table %s (like %s including defaults including constraints including indexes including comments)",
                nextMonthTable, monthTable);
        jdbcTemplate.execute(createTableSql);
    }

    public boolean hasTable(CreateTableConfig tableConfig) {
        String tableTemplate = tableConfig.getTableTemplate();
        String dateFormat = tableConfig.getDateFormat();

        Date now = new Date();
        String monthTable = ExpressionDateFormatUtil.format(tableTemplate, now, dateFormat);
        boolean has = hasTable(monthTable);
        return has;
    }

    public boolean hasTable(String table) {
        boolean has;
        try {
            String findTableSql = String.format("select relname from pg_class where relname = '%s'", table);
            String t = jdbcTemplate.queryForObject(findTableSql, String.class);
            if (StringUtils.isEmpty(t)) {
                has = false;
            } else {
                has = true;
            }
        } catch (EmptyResultDataAccessException e) {
            has = false;
        }
        return has;
    }
}
