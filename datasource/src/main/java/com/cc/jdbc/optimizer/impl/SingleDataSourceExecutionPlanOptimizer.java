package com.cc.jdbc.optimizer.impl;

import com.cc.database.meta.DatabaseProductName;
import com.cc.jdbc.optimizer.ExecutionPlanOptimizer;
import com.cc.jdbc.optimizer.OptimizedExecutionPlan;

import java.util.List;

/**
 * Created by miyan(弥彦) on 2017/1/6 16:24.
 */
public class SingleDataSourceExecutionPlanOptimizer implements ExecutionPlanOptimizer {

    public static final long DEFAULT_TIMEOUT_FOR_EACH_TABLE = 100;

    private String dataSourceName;

    private DatabaseProductName databaseProductName;

    public SingleDataSourceExecutionPlanOptimizer(String dataSourceName, DatabaseProductName databaseProductName) {
        this.dataSourceName = dataSourceName;
        this.databaseProductName = databaseProductName;
    }

    @Override
    public OptimizedExecutionPlan apply(String sequenceSQLStatement, List<Object> parameters) {
        return new DefaultExecutionPlan();
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public DatabaseProductName getDatabaseProductName() {
        return databaseProductName;
    }

    public void setDatabaseProductName(DatabaseProductName databaseProductName) {
        this.databaseProductName = databaseProductName;
    }
}
