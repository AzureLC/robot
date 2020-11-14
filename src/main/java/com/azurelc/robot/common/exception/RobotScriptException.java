package com.azurelc.robot.common.exception;

public class RobotScriptException extends RuntimeException {
    public RobotScriptException() {
        super("");
    }

    public RobotScriptException(String message) {
        super(message);
    }
}
