/**
 * 7mxing.com Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.cc.sequence.service.entities;


import com.cc.sequence.common.exception.SequenceException;
import com.cc.sequence.datasouece.jdbc.optimizer.OptimizedExecutionPlan;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * @author <a href="mailto:xiang.yang@7mxing.com">Yang Xiang</a>
 * @version $Id: CombinateSequenceRange.java
 */
public class CombinateSequenceRange implements Serializable {

    public static final String         NAME             = "name";
    public static final String         VALUE            = "value";
    public static final String         MIN_VALUE        = "min_value";
    public static final String         MAX_VALUE        = "max_value";
    public static final String         STEP             = "step";
    public static final String         GMT_CREATE       = "gmt_create";
    public static final String         GMT_MODIFIED     = "gmt_modified";

    /**  */
    private static final long          serialVersionUID = 6261899134306522938L;

    private Date                       systemDate;

    private String                     name;

    private long                       step;

    private long                       min;

    private long                       max;

    private volatile long              value;

    private long                       latestValue;

    private final AtomicLong           localValue = new AtomicLong();

    private String                     sequenceSourceId;

    private String                     databaseIndex;

    private String                     databaseShardValue;

    private String                     logicalTableName;

    private String                     tableShardValue;

    private String                     targetTableName;

    private String                     elasticDataSourceIndex;

    private String                     logicDBIndex ;

    /**
     * A status to indicate whether it needs to refresh
     */
    private volatile boolean           isOver           = false;

    private OptimizedExecutionPlan optimizedExecutionPlan;

    public CombinateSequenceRange() {

    }

    public CombinateSequenceRange(String sequenceName, long min, long max, long step, long value) {
        setName(sequenceName);
        setMax(max);
        setMin(min);
        setStep(step);
        setValue(value);
        //todo
//        this.value = value;
//        localValue.set(value);
    }

    public Date getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Date systemDate) {
        this.systemDate = systemDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStep() {
        return step;
    }

    public void setStep(long step) {
        this.step = step;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        synchronized (localValue){
            this.value = value;
            localValue.set(value);
        }
    }

    public boolean isOver() {
        //It is over
        if( isOver ){
            return isOver;
        }

        return !((localValue.get() < getStep() + value) && localValue.get() <= getMax());
    }

    public long getNextValue() {
        return localValue.getAndIncrement();
    }

    public boolean isQualifiedValue(long value) {
        if (value < 0 || value > getMax() || value < getMin())
            return false;
        return true;
    }

    public void validate() throws SequenceException {
        if (min >= max || min + step > max)
            throw new SequenceException(
                "ERROR ## CombinateSequenceRange is not validated as minValue=" + min
                        + ",maxValue=" + max + ",step=" + step);
    }

    public String getSequenceSourceId() {
        return sequenceSourceId;
    }

    public void setSequenceSourceId(String sequenceSourceId) {
        this.sequenceSourceId = sequenceSourceId;
    }

    public String getDatabaseIndex() {
        return databaseIndex;
    }

    public void setDatabaseSerialNumber(String databaseIndex) {
        this.databaseIndex = databaseIndex;
    }

    public String getDatabaseShardValue() {
        return databaseShardValue;
    }

    public void setDatabaseShardValue(String databaseShardValue) {
        this.databaseShardValue = databaseShardValue;
    }

    public String getLogicalTableName() {
        return logicalTableName;
    }

    public void setLogicalTableName(String logicalTableName) {
        this.logicalTableName = logicalTableName;
    }

    public String getTableShardValue() {
        return tableShardValue;
    }

    public void setTableShardValue(String tableShardValue) {
        this.tableShardValue = tableShardValue;
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    public long getLatestValue() {
        return latestValue;
    }

    public void setLatestValue(long latestValue) {
        this.latestValue = latestValue;
    }

    /**
     * Getter method for property <tt>elasticDataSourceIndex</tt>.
     * 
     * @return property value of elasticDataSourceIndex
     */
    public String getElasticDataSourceIndex() {
        return elasticDataSourceIndex;
    }

    /**
     * Setter method for property <tt>elasticDataSourceIndex</tt>.
     * 
     * @param elasticDataSourceIndex value to be assigned to property elasticDataSourceIndex
     */
    public void setElasticDataSourceInternalIndex(String elasticDataSourceIndex) {
        this.elasticDataSourceIndex = elasticDataSourceIndex;
    }

    public void cloneContextValue(CombinateSequenceRange cloneSource) {
        if (null != cloneSource) {
            setDatabaseSerialNumber(cloneSource.getDatabaseIndex());
            setDatabaseShardValue(cloneSource.getDatabaseShardValue());
            setTableShardValue(cloneSource.getTableShardValue());
            setTargetTableName(cloneSource.getTargetTableName());
            setLogicalTableName(cloneSource.getLogicalTableName());
//            setRouteCondition(cloneSource.getRouteCondition());
            setSequenceSourceId(cloneSource.getSequenceSourceId());
            setElasticDataSourceInternalIndex(cloneSource.getElasticDataSourceIndex());
        }
    }

    public String getLogicDBIndex() {
        return logicDBIndex;
    }

    public void setLogicDBIndex(String logicDBIndex) {
        this.logicDBIndex = logicDBIndex;
    }

    public void setOver() {
        this.isOver = true;
    }
    
    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "CombinateSequenceRange [systemDate=" + systemDate + ", name=" + name + ", step="
               + step + ", min=" + min + ", max=" + max + ", value=" + value + ", latestValue="
               + latestValue + ", localValue=" + localValue + ", sequenceSourceId="
               + sequenceSourceId + ", databaseIndex=" + databaseIndex + ", databaseShardValue="
               + databaseShardValue + ", logicalTableName=" + logicalTableName
               + ", tableShardValue=" + tableShardValue + ", targetTableName=" + targetTableName
               + ", elasticDataSourceIndex=" + elasticDataSourceIndex + "]";
//               + , routeCondition=routeCondition + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((databaseIndex == null) ? 0 : databaseIndex.hashCode());
        result = prime * result
                 + ((databaseShardValue == null) ? 0 : databaseShardValue.hashCode());
        result = prime * result
                 + ((elasticDataSourceIndex == null) ? 0 : elasticDataSourceIndex.hashCode());
        result = prime * result + (isOver ? 1231 : 1237);
        result = prime * result + ((logicalTableName == null) ? 0 : logicalTableName.hashCode());
        result = prime * result + (int) (max ^ (max >>> 32));
        result = prime * result + (int) (min ^ (min >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((sequenceSourceId == null) ? 0 : sequenceSourceId.hashCode());
        result = prime * result + ((tableShardValue == null) ? 0 : tableShardValue.hashCode());
        result = prime * result + ((targetTableName == null) ? 0 : targetTableName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CombinateSequenceRange other = (CombinateSequenceRange) obj;
        if (databaseIndex == null) {
            if (other.databaseIndex != null)
                return false;
        } else if (!databaseIndex.equals(other.databaseIndex))
            return false;
        if (databaseShardValue == null) {
            if (other.databaseShardValue != null)
                return false;
        } else if (!databaseShardValue.equals(other.databaseShardValue))
            return false;
        if (elasticDataSourceIndex == null) {
            if (other.elasticDataSourceIndex != null)
                return false;
        } else if (!elasticDataSourceIndex.equals(other.elasticDataSourceIndex))
            return false;
        if (isOver != other.isOver)
            return false;
        if (logicalTableName == null) {
            if (other.logicalTableName != null)
                return false;
        } else if (!logicalTableName.equals(other.logicalTableName))
            return false;
        if (max != other.max)
            return false;
        if (min != other.min)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (sequenceSourceId == null) {
            if (other.sequenceSourceId != null)
                return false;
        } else if (!sequenceSourceId.equals(other.sequenceSourceId))
            return false;
        if (tableShardValue == null) {
            if (other.tableShardValue != null)
                return false;
        } else if (!tableShardValue.equals(other.tableShardValue))
            return false;
        if (targetTableName == null) {
            if (other.targetTableName != null)
                return false;
        } else if (!targetTableName.equals(other.targetTableName))
            return false;
        return true;
    }

	public void setOptimizedExecutionPlan(OptimizedExecutionPlan optimizedExecutionPlan) {
		this.optimizedExecutionPlan = optimizedExecutionPlan;
	}

	/**
	 * Getter method for property <tt>optimizedExecutionPlan</tt>.
	 *
	 * @return property value of optimizedExecutionPlan
	 */
	public OptimizedExecutionPlan getOptimizedExecutionPlan() {
		return optimizedExecutionPlan;
	}

}