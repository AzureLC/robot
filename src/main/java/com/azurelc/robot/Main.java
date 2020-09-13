package com.azurelc.robot;

import com.azurelc.robot.common.entity.Coordinate;
import com.azurelc.robot.common.robotsingle.RobotSingleFactory;
import com.azurelc.robot.imagehandler.FindImageImpl;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 3; i++) {
                long startTime = System.currentTimeMillis();
                Coordinate coordinate = FindImageImpl.getInstance().doFindImage(new Coordinate(0, 0),
                        new Coordinate(200, 200), 1.0, "image", i + ".bmp");
                System.out.println(System.currentTimeMillis() - startTime);
                Robot robot = RobotSingleFactory.getRobotSingle();
                robot.mouseMove(coordinate.getX(), coordinate.getY());
            }
        } catch (URISyntaxException | IOException | AWTException e) {
            e.printStackTrace();
        }
    }
}
