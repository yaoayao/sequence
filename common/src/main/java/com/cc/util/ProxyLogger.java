package com.cc.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by miyan(弥彦) on 2017/1/9 18:01.
 * 这个类是为了在初始化日志变量是不主动初始化日志加载器
 */
public class ProxyLogger extends Logger {

    public ProxyLogger(String name) {
        super(name);
    }

    public ProxyLogger(String name,String level){
        super(name);
        setLevel(Level.toLevel(level,Level.INFO));
    }
    public void warn(Object message) {
        LogUtil.warn(getName(), message);
    }

    public void warn(Object message, Throwable t) {
        LogUtil.warn(getName(), message, t);
    }

    public void debug(Object message) {
        LogUtil.debug(getName(), message);
    }

    public void debug(Object message, Throwable t) {
        LogUtil.debug(getName(), message, t);
    }

    public void info(Object message) {
        LogUtil.info(getName(), message);
    }

    public void info(Object message, Throwable t) {
        LogUtil.info(getName(), message, t);
    }

    public void error(Object message) {
        LogUtil.error(getName(), message);
    }

    public void error(Object message, Throwable t) {
        LogUtil.error(getName(), message, t);
    }

    public void fatal(Object message) {
        LogUtil.fatal(getName(), message);
    }

    public void fatal(Object message, Throwable t) {
        LogUtil.fatal(getName(), message, t);
    }

    public boolean isDebugEnabled() {
        return LogUtil.isDebugEnabled(getName());
    }

    public boolean isInfoEnabled(String loggerName) {
        return LogUtil.isInfoEnabled(getName());
    }

    public boolean isTraceEnabled(String loggerName) {
        return LogUtil.isTraceEnabled(getName());
    }}
