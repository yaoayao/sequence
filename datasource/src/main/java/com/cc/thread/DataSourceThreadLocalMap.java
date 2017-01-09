package com.cc.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miyan(弥彦) on 2017/1/6 19:37.
 */
public class DataSourceThreadLocalMap {

    protected final static ThreadLocal<Map<Object, Object>> threadContext = new InternalThreadLocal();

    public DataSourceThreadLocalMap() {
    }

    private static class InternalThreadLocal extends ThreadLocal<Map<Object, Object>> {
        @Override
        protected Map<Object, Object> initialValue() {
            return new HashMap<Object, Object>() {
                private static final long serialVersionUID = 7046144397609805828L;
            };
        }
    }
    protected static Map<Object, Object> getInternalThreadLocalMap() {
        return threadContext.get();
    }
}
