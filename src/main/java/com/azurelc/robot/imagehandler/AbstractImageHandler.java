package com.azurelc.robot.imagehandler;

import com.azurelc.robot.common.entity.Coordinate;
import com.azurelc.robot.common.entity.FivePoint;
import com.azurelc.robot.common.entity.Point;
import com.azurelc.robot.common.utils.RobotUtils;
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class AbstractImageHandler {
    protected abstract FivePoint findFivePoint(BufferedImage bfImage, int[][] imageRGB, int ignoreRgbColor);

    public Coordinate doFindImage(Coordinate startCoordinate, Coordinate endCoordinate, BufferedImage keyImageBuff,
            int[][] keyImageRGB, int ignoreRgbColor, double matchDegree) throws URISyntaxException, IOException,
            AWTException {
        FivePoint fivePoint = findFivePoint(keyImageBuff, keyImageRGB, ignoreRgbColor);
        BufferedImage screenShot = RobotUtils.getScreenShot(startCoordinate, endCoordinate);
        int[][] screenShotRGB = RobotUtils.getImageGRB(screenShot);

        // 匹配四个点 + 精确匹配
        return matchFivePoint(fivePoint, keyImageBuff, keyImageRGB, screenShot, screenShotRGB, ignoreRgbColor,
                matchDegree);
    }

    private Coordinate matchFivePoint(FivePoint fivePoint, BufferedImage keyImageBuff, int[][] keyImageRGB,
            BufferedImage screenShot, int[][] screenShotRGB, int ignoreRgbColor, double matchDegree) {
        int height = screenShot.getHeight() - keyImageBuff.getHeight() + 1;
        int width = screenShot.getWidth() - keyImageBuff.getWidth() + 1;
        Point a = fivePoint.getA();
        Point b = fivePoint.getB();
        Point c = fivePoint.getC();
        Point d = fivePoint.getD();
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if ((screenShotRGB[h][w] ^ a.getRgb()) == 0 && (screenShotRGB[h + (b.getY() - a.getY())][w + (b.getX() - a.getX())] ^ b.getRgb()) == 0 && (screenShotRGB[h + (c.getY() - a.getY())][w + (c.getX() - a.getX())] ^ c.getRgb()) == 0 && (screenShotRGB[h + (d.getY() - a.getY())][w + (d.getX() - a.getX())] ^ d.getRgb()) == 0) {
                    // 四个点匹配上了
                    Coordinate coordinate = new Coordinate(w - a.getX(), h - a.getY());
                    // 精确匹配
                    if (matchAllPoint(coordinate, keyImageBuff, keyImageRGB, screenShotRGB, ignoreRgbColor,
                            matchDegree)) {
                        coordinate.setX(coordinate.getX() + keyImageBuff.getWidth() / 2);
                        coordinate.setY(coordinate.getY() + keyImageBuff.getHeight() / 2);
                        return coordinate;
                    }
                }
            }
        }
        return new Coordinate(-1, -1);
    }

    private boolean matchAllPoint(Coordinate coordinate, BufferedImage keyImageBuff, int[][] keyImageRGB,
            int[][] screenShotRGB, int ignoreRgbColor, double matchDegree) {
        int matchTimes = 0;
        int height = keyImageBuff.getHeight();
        int width = keyImageBuff.getWidth();
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (isIgnoreRgbColor(keyImageRGB[h][w], ignoreRgbColor)) {
                    matchTimes++;
                } else if ((keyImageRGB[h][w] ^ screenShotRGB[h + coordinate.getY()][w + coordinate.getX()]) == 0) {
                    matchTimes++;
                }
                if (matchTimes * 1.0 / (height * width) >= matchDegree) {
                    return true;
                }
            }
        }
        coordinate.setX(-1);
        coordinate.setY(-1);
        return false;
    }

    protected abstract boolean isIgnoreRgbColor(int keyImagePointRgbColor, int ignoreRgbColor);
}
