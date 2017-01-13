package com.cc.sequence.datasouece.jdbc.optimizer;

import java.util.List;

/**
 * Created by miyan(弥彦) on 2017/1/6 16:16.
 */
public interface ExecutionPlanOptimizer {
    OptimizedExecutionPlan apply(String sequenceSQLStatement , List<Object> parameters);
}
