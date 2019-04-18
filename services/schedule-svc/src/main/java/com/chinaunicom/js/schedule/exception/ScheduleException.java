package com.chinaunicom.js.schedule.exception;

/**
 * Created by GaoHaoYu on 2019/3/29.
 */
public class ScheduleException extends Exception {

    public ScheduleException() {
        super();
    }

    public ScheduleException(String message) {
        super(message);
    }

    public ScheduleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScheduleException(Throwable cause) {
        super(cause);
    }

    protected ScheduleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
