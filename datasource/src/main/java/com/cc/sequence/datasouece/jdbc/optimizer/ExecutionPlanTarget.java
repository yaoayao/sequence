package com.cc.sequence.datasouece.jdbc.optimizer;

import java.util.List;

/**
 * Created by miyan(弥彦) on 2017/1/6 15:25.
 * 执行计划接口
 */
public interface ExecutionPlanTarget {

    String getDataSourceSerialNumber();

    String getAppliedTableRuleValue();

    String getAppliedDatabaseRuleValue();

    String getAppliedElasticDataSourceRuleValue();

    List<String> getTablesNames();

}
