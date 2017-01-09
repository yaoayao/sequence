package com.cc.jdbc.optimizer.impl;

import com.cc.jdbc.optimizer.ExecutionPlanTarget;
import com.cc.jdbc.optimizer.OptimizedExecutionPlan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miyan(弥彦) on 2017/1/6 15:56.
 */
public class DefaultExecutionPlan implements OptimizedExecutionPlan {

    public static final String DEFAULT_SHARDING_VALUE = "0";

    private List<ExecutionPlanTarget> targetList;

    public DefaultExecutionPlan() {
        targetList = new ArrayList<ExecutionPlanTarget>();
        DefaultExecutionPlanTarget target = new DefaultExecutionPlanTarget();
        target.setAppliedDatabaseRuleValue(DEFAULT_SHARDING_VALUE);
        target.setAppliedTableRuleValue(DEFAULT_SHARDING_VALUE);
        target.setAppliedElasticDataSource(DEFAULT_SHARDING_VALUE);
        targetList.add(target);
    }

    @Override
    public List<ExecutionPlanTarget> getExecutionPlanTargets() {
        return targetList;
    }
}
