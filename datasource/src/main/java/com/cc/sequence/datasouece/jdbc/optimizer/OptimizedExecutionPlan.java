package com.cc.sequence.datasouece.jdbc.optimizer;

import java.util.List;

/**
 * Created by miyan(弥彦) on 2017/1/6 15:53.
 * 最优执行计划
 */
public interface OptimizedExecutionPlan {
    List<ExecutionPlanTarget> getExecutionPlanTargets();
}
