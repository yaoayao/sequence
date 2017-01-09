package com.cc.util;


import org.apache.log4j.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by miyan(弥彦) on 2017/1/9 11:07.
 */
public class LogUtil {

    private static final String LAYOUT_PATTERN = "%d-%m%n";
    private static final String DAY_DATE_PATTERN = "'.'yyyy-MM-dd";
    private static final String CHAR_SET = Charset.defaultCharset().name();
    private static final String DAILY_APPENDER_NAME = "_DAILY_APPENDER_NAME";
    private static ConcurrentHashMap<String, Logger> loggers = new ConcurrentHashMap<String, Logger>();
    private static final Level DEFAULT_LEVEL = Level.INFO;

    private static String loggingRoot = "";
    private static String appName = "";

    public static void setLoggingRoot(String root) {
        if (StringUtil.isEmpty(root)) {
            loggingRoot = root;

        }
    }

    public static void setLoggingRoot(String root, String loggingLevel) {
        // Clear loggers map1
        if (StringUtil.isEmpty(loggingRoot)) {
            loggingRoot = root;
            initLogger(LoggerName.STATISTICS, Level.toLevel(loggingLevel, DEFAULT_LEVEL));
            initLogger(LoggerName.MONITOR, Level.toLevel(loggingLevel, DEFAULT_LEVEL));
        }

    }

    public static boolean isDebugEnabled(String loggerName) {
        Logger logger = getInternalLogger(loggerName);
        return logger.isDebugEnabled();
    }

    public static boolean isInfoEnabled(String loggerName) {
        Logger logger = getInternalLogger(loggerName);
        return logger.isInfoEnabled();
    }

    public static boolean isTraceEnabled(String loggerName) {
        Logger logger = getInternalLogger(loggerName);
        return logger.isTraceEnabled();
    }

    // init the appName
    public static void setAppName(String realAppName) {
        if (StringUtil.isEmpty(loggingRoot)) {
            appName = realAppName;
        }
    }

    public static void info(String loggerName, Object msg) {
        Logger logger = getInternalLogger(loggerName);
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }

    public static void info(String loggerName, Object msg, Throwable t) {
        Logger logger = getInternalLogger(loggerName);
        if (logger.isInfoEnabled()) {
            logger.info(msg, t);
        }
    }

    public static void debug(String loggerName, Object msg) {
        Logger logger = getInternalLogger(loggerName);
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    public static void debug(String loggerName, Object msg, Throwable t) {
        Logger logger = getInternalLogger(loggerName);
        if (logger.isDebugEnabled()) {
            logger.debug(msg, t);
        }
    }

    public static void warn(String loggerName, Object msg) {
        Logger logger = getInternalLogger(loggerName);
        logger.warn(msg);
    }

    public static void warn(String loggerName, Object msg, Throwable t) {
        Logger logger = getInternalLogger(loggerName);
        logger.warn(msg, t);
    }

    public static void error(String loggerName, Object msg) {
        Logger logger = getInternalLogger(loggerName);
        logger.error(msg);
    }

    public static void error(String loggerName, Object msg, Throwable t) {
        Logger logger = getInternalLogger(loggerName);
        logger.error(msg, t);
    }

    public static void fatal(String loggerName, Object msg) {
        Logger logger = getInternalLogger(loggerName);
        logger.fatal(msg);
    }

    public static void fatal(String loggerName, Object msg, Throwable t) {
        Logger logger = getInternalLogger(loggerName);
        logger.fatal(msg, t);
    }

    public static Logger getLogger(String loggerName) {
        return new ProxyLogger(loggerName);
    }

    public static Logger getLogger(String loggerName, String loggingLevel) {
        return new ProxyLogger(loggerName, loggingLevel);
    }

    public static Logger getInternalLogger(String name) {
        Logger logger = loggers.get(name);
        if (logger == null) {
            initLogger(name, null);
        }
        return logger;
    }

    public static Logger initLogger(String loggerName, Level level) {
        Logger logger = Logger.getLogger(loggerName);
        PatternLayout patternLayout = new PatternLayout(LAYOUT_PATTERN);
        WriterAppender appender = null;
        try {
            if (!StringUtil.isEmpty(loggingRoot)) {
                StringBuilder sb = new StringBuilder();
                sb.append(loggingRoot).append(File.separator);
                if (!StringUtil.isEmpty(appName)) {
                    sb.append(appName).append(File.separator);
                }
                String path = sb.toString();
                File file = new File(path);

                if (!file.exists()) {
                    file.mkdirs();
                }
                String logFileName = path + loggerName + ".log";
                appender = new DailyRollingFileAppender(patternLayout, logFileName, DAY_DATE_PATTERN);
                ((FileAppender) appender).setAppend(true);
            } else {
                appender = new ConsoleAppender();
                appender.activateOptions();
                appender.setLayout(patternLayout);
                appender.setEncoding(CHAR_SET);
                appender.setName(DAILY_APPENDER_NAME);
            }
        } catch (IOException e) {
            throw new RuntimeException("init logger failed,because of:" + e);
        }
        if (appender != null) {
            logger.removeAllAppenders();
            logger.addAppender(appender);
        }
        if (level != null) {
            logger.setLevel(level);
        } else {
            logger.setLevel(DEFAULT_LEVEL);
        }
        logger.setAdditivity(false);
        Logger old = loggers.putIfAbsent(loggerName, logger);
        return (old == null) ? logger : old;
    }

    public static class LoggerName {
        public static final String MONITOR = "monitor";
        public static final String STATISTICS = "statistics";//统计
    }

}
