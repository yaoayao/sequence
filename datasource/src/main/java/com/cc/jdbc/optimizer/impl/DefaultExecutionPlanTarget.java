package com.cc.jdbc.optimizer.impl;

import com.cc.jdbc.optimizer.ExecutionPlanTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miyan(弥彦) on 2017/1/6 15:40.
 */
public class DefaultExecutionPlanTarget implements ExecutionPlanTarget {
    //todo why protect?
    protected static final String PHYSICAL_DATA_SOURCE = "physicalDataSource";

    private String dataSourceSerialNumber = PHYSICAL_DATA_SOURCE;

    private String appliedElasticDataSource;

    private String appliedTableRuleValue;

    private String appliedDatabaseRuleValue;

    private List<String> tableNames = new ArrayList<String>();
    @Override
    public String getDataSourceSerialNumber() {
        return dataSourceSerialNumber;
    }

    public void setDataSourceSerialNumber(String dataSourceSerialNumber) {
        this.dataSourceSerialNumber = dataSourceSerialNumber;
    }

    public void setAppliedElasticDataSource(String appliedElasticDataSource) {
        this.appliedElasticDataSource = appliedElasticDataSource;
    }

    public void setAppliedTableRuleValue(String appliedTableRuleValue) {
        this.appliedTableRuleValue = appliedTableRuleValue;
    }

    public void setAppliedDatabaseRuleValue(String appliedDatabaseRuleValue) {
        this.appliedDatabaseRuleValue = appliedDatabaseRuleValue;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

    @Override
    public String getAppliedTableRuleValue() {
        return appliedTableRuleValue;
    }

    @Override
    public String getAppliedDatabaseRuleValue() {
        return appliedDatabaseRuleValue;
    }

    @Override
    public String getAppliedElasticDataSourceRuleValue() {
        return appliedElasticDataSource;
    }

    @Override
    public List<String> getTablesNames() {
        return tableNames;
    }
}
