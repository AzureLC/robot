package com.azurelc.robot.common.robotsingle;

import java.awt.AWTException;
import java.awt.Robot;

public final class RobotSingleFactory {
    private volatile static Robot robot = null;

    private RobotSingleFactory() {
    }

    public static Robot getRobotSingle() throws AWTException {
        if (robot == null) {
            synchronized (RobotSingleFactory.class) {
                if (robot == null) {
                    robot = new Robot();
                }
            }
        }
        return robot;
    }
}
