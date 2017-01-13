package com.cc.sequence.datasouece.shared.weight;

/**
 * Created by miyan(弥彦) on 2017/1/6 15:08.
 */
public class CombineWeightInstance {

    private String instanceId;

    private int readWeight = 0;

    private int writeWeight = 0;

    private int readPriority = Integer.MAX_VALUE;

    private int writePriority = Integer.MAX_VALUE;

    private int index;

    public CombineWeightInstance(String instanceId, int readWeight, int writeWeight, int readPriority, int writePriority) {
        this.instanceId = instanceId;
        this.readWeight = readWeight;
        this.writeWeight = writeWeight;
        this.readPriority = readPriority;
        this.writePriority = writePriority;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public int getReadWeight() {
        return readWeight;
    }

    public void setReadWeight(int readWeight) {
        this.readWeight = readWeight;
    }

    public int getWriteWeight() {
        return writeWeight;
    }

    public void setWriteWeight(int writeWeight) {
        this.writeWeight = writeWeight;
    }

    public int getReadPriority() {
        return readPriority;
    }

    public void setReadPriority(int readPriority) {
        this.readPriority = readPriority;
    }

    public int getWritePriority() {
        return writePriority;
    }

    public void setWritePriority(int writePriority) {
        this.writePriority = writePriority;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CombineWeightInstance that = (CombineWeightInstance) o;

        if (index != that.index) return false;
        if (readPriority != that.readPriority) return false;
        if (readWeight != that.readWeight) return false;
        if (writePriority != that.writePriority) return false;
        if (writeWeight != that.writeWeight) return false;
        if (!instanceId.equals(that.instanceId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instanceId.hashCode();
        result = 31 * result + readWeight;
        result = 31 * result + writeWeight;
        result = 31 * result + readPriority;
        result = 31 * result + writePriority;
        result = 31 * result + index;
        return result;
    }
}
