package com.tiho.common.entity;

/**
 * Created by TimoRD on 2017/5/25.
 */
public class CreateTableConfig {

    private String tableTemplate;
    private String dateFormat;
    private boolean constraints = true;
    private boolean indexes = true;
    private boolean comments = true;

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

    public boolean isConstraints() {
        return constraints;
    }

    public void setConstraints(boolean constraints) {
        this.constraints = constraints;
    }

    public boolean isIndexes() {
        return indexes;
    }

    public void setIndexes(boolean indexes) {
        this.indexes = indexes;
    }

    public boolean isComments() {
        return comments;
    }

    public void setComments(boolean comments) {
        this.comments = comments;
    }

    public CreateTableConfig() {
    }

    public CreateTableConfig(String tableTemplate, String dateFormat) {
        this.tableTemplate = tableTemplate;
        this.dateFormat = dateFormat;
    }

    public CreateTableConfig(String tableTemplate, String dateFormat, boolean constraints, boolean indexes, boolean comments) {
        this.tableTemplate = tableTemplate;
        this.dateFormat = dateFormat;
        this.constraints = constraints;
        this.indexes = indexes;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "CreateTableConfig{" +
                "tableTemplate='" + tableTemplate + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                '}';
    }
}
