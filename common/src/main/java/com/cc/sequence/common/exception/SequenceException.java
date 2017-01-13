package com.cc.sequence.common.exception;

import java.sql.SQLException;

/**
 * Created by miyan(弥彦) on 2017/1/13 16:05.
 */
public class SequenceException extends SQLException {

    private static final long serialVersionUID = 1597548643403652054L;

    private String message;

    public SequenceException(String message) {
        super();
        this.message = message;
    }

    public SequenceException(Throwable cause, String message) {
        super(message,cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
