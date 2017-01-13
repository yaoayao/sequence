package com.cc.sequence.common.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by miyan(弥彦) on 2017/1/9 11:07.
 * sql  objects close util
 */
public class ConnectionUtil {

    public static final Logger logger = LogUtil.getLogger(LogUtil.LoggerName.MONITOR);

    public static void close(Object... objects){
        if (objects != null){
            for (Object o :objects){
                if (o instanceof ResultSet){
                    try {
                        ((ResultSet)o).close();
                    } catch (SQLException e) {
                        logger.error("close result error!" , e);
                    }
                }
                if (o instanceof Statement){
                    try {
                        ((Statement)o).close();
                    } catch (SQLException e) {
                        logger.error("close statement error!" , e);
                    }
                }
                if (o instanceof Connection){
                    try {
                        ((Connection)o).close();
                    } catch (SQLException e) {
                        logger.error("close connect error!" ,e);
                    }
                }
            }
        }
    }

    public static void close(ResultSet resultSet , Statement statement , Connection connection){
        try {
            if (resultSet != null && !resultSet.isClosed()){
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error("close result error!" , e);
        }
        try {
            if (statement != null && !statement.isClosed()){
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("close statement error!" , e);
        }
        try {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("close connection error!" , e);
        }
    }
}
