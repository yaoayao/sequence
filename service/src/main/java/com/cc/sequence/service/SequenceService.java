package com.cc.sequence.service;

/**
 * Created by miyan(弥彦) on 2017/1/13 16:03.
 */
public interface SequenceService {

    public long nextValue(String SequenceName);

    public long nextIncrementValue(String sequenceName);


}
