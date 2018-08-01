package com.tiho.common.entity;

/**
 * Created by TimoRD on 2017/5/25.
 */
public class CreateTableConfig {

    private String tableTemplate;
    private String dateFormat;

    public String getTableTemplate() {
        return tableTemplate;
    }

    public void setTableTemplate(String tableTemplate) {
        this.tableTemplate = tableTemplate;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public CreateTableConfig() {
    }

    public CreateTableConfig(String tableTemplate, String dateFormat) {
        this.tableTemplate = tableTemplate;
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "CreateTableConfig{" +
                "tableTemplate='" + tableTemplate + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                '}';
    }
}
