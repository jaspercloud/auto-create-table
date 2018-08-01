package com.tiho.common.support;

import com.tiho.common.entity.CreateTableConfig;
import com.tiho.common.exception.TableNotFoundInitializerError;
import com.tiho.common.util.ExpressionDateFormatUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TimoRD on 2017/11/23.
 */
@EnableScheduling
public class AutoCreateTableTask implements InitializingBean {

    private Logger logger = Logger.getLogger(getClass());

    private CreateTableFactory createTableFactory;
    private List<CreateTableConfig> createTableConfigList = new ArrayList<>();

    public void addCreateTableConfig(CreateTableConfig createTableConfig) {
        createTableConfigList.add(createTableConfig);
    }

    public AutoCreateTableTask(CreateTableFactory createTableFactory) {
        this.createTableFactory = createTableFactory;
    }

    public AutoCreateTableTask(CreateTableFactory createTableFactory, List<CreateTableConfig> createTableConfigList) {
        this.createTableFactory = createTableFactory;
        this.createTableConfigList.addAll(createTableConfigList);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkTable();
        autoCreateTable();
    }

    private void checkTable() {
        for (CreateTableConfig tableConfig : createTableConfigList) {
            boolean hasTable = createTableFactory.hasTable(tableConfig);
            if (false == hasTable) {
                String table = ExpressionDateFormatUtil.format(tableConfig.getTableTemplate(), new Date(), tableConfig.getDateFormat());
                throw new TableNotFoundInitializerError(table);
            }
        }
    }

    @Scheduled(cron = "${autoCreateTable.cron:0 0 * * * ?}")
    public void autoCreateTable() {
        for (CreateTableConfig tableConfig : createTableConfigList) {
            try {
                createTableFactory.createTable(tableConfig);
            } catch (Throwable t) {
                logger.error(t.getMessage(), t);
            }
        }
    }
}
