package com.cc.sequence.datasouece.test.jdbc.optimizer;

import com.cc.sequence.datasouece.meta.DatabaseProductName;
import com.cc.sequence.datasouece.jdbc.optimizer.ExecutionPlanOptimizer;
import com.cc.sequence.datasouece.jdbc.optimizer.ExecutionPlanTarget;
import com.cc.sequence.datasouece.jdbc.optimizer.OptimizedExecutionPlan;
import com.cc.sequence.datasouece.jdbc.optimizer.impl.SingleDataSourceExecutionPlanOptimizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miyan(弥彦) on 2017/1/9 10:54.
 */
public class OptimizerTest {
    public void single(){
        ExecutionPlanOptimizer executionPlanOptimizer = new SingleDataSourceExecutionPlanOptimizer("", DatabaseProductName.MYSQL);
        OptimizedExecutionPlan optimizedExecutionPlan = executionPlanOptimizer.apply("",new ArrayList<Object>());
        List<ExecutionPlanTarget> executionPlanTargets = optimizedExecutionPlan.getExecutionPlanTargets();

    }
}
