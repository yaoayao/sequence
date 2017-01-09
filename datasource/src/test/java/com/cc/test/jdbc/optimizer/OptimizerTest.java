package com.cc.test.jdbc.optimizer;

import com.cc.database.meta.DatabaseProductName;
import com.cc.jdbc.optimizer.ExecutionPlanOptimizer;
import com.cc.jdbc.optimizer.ExecutionPlanTarget;
import com.cc.jdbc.optimizer.OptimizedExecutionPlan;
import com.cc.jdbc.optimizer.impl.SingleDataSourceExecutionPlanOptimizer;

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
