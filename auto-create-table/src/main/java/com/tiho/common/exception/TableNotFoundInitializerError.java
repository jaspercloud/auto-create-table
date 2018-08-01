package com.tiho.common.exception;

/**
 * Created by TimoRD on 2017/11/23.
 */
public class TableNotFoundInitializerError extends ExceptionInInitializerError {

    public TableNotFoundInitializerError() {
    }

    public TableNotFoundInitializerError(Throwable thrown) {
        super(thrown);
    }

    public TableNotFoundInitializerError(String s) {
        super(s);
    }
}
