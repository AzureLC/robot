package com.azurelc.robot.imagehandler.findword;

import com.azurelc.robot.common.entity.Coordinate;
import com.azurelc.robot.common.entity.FivePoint;
import com.azurelc.robot.common.entity.Point;
import com.azurelc.robot.common.utils.RobotUtil;
import com.azurelc.robot.imagehandler.AbstractImageHandler;
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public class WordHandlerImplAbstract extends AbstractImageHandler implements WordHandler {
    private WordHandlerImplAbstract() {
    }

    private static class WordHandlerSingle {

        private static final WordHandler INSTANCE = new WordHandlerImplAbstract();
    }


    public static WordHandler getInstance() {
        return WordHandlerSingle.INSTANCE;
    }

    @Override
    public Coordinate findWordHandler(Coordinate startCoordinate, Coordinate endCoordinate,
            BufferedImage keyImageBuff, int[][] keyImageRGB, int ignoreRgbColor, double matchDegree) throws URISyntaxException, IOException, AWTException {
        return doFindImage(startCoordinate, endCoordinate, keyImageBuff, keyImageRGB, ignoreRgbColor, matchDegree);
    }

    @Override
    protected FivePoint findFivePoint(BufferedImage bfImage, int[][] imageRGB, int ignoreRgbColor) {
        FivePoint fivePoint = new FivePoint();
        int height = bfImage.getHeight();
        int width = bfImage.getWidth();
        for (int h = 1; h < height; h++) {
            Point a = null;
            Point b = null;
            for (int w = 1; w < width; w++) {
                if ((imageRGB[h][w] ^ ignoreRgbColor) == 0) {
                    continue;
                }
                a = new Point(w, h, imageRGB[h][w]);
                break;
            }
            for (int w = width - 2; w >= 0; w--) {
                if ((imageRGB[h][w] ^ ignoreRgbColor) == 0) {
                    continue;
                }
                b = new Point(w, h, imageRGB[h][w]);
                break;
            }
            if (a != null && b != null && !RobotUtil.isEqualCoordinate(a, b)) {
                fivePoint.setA(a);
                fivePoint.setB(b);
                break;
            }
        }

        for (int h = height - 2; h >= 0; h--) {
            Point c = null;
            Point d = null;
            for (int w = 1; w < width; w++) {
                if ((imageRGB[h][w] ^ ignoreRgbColor) == 0) {
                    continue;
                }
                c = new Point(w, h, imageRGB[h][w]);
                break;
            }
            for (int w = width - 2; w >= 0; w--) {
                if ((imageRGB[h][w] ^ ignoreRgbColor) == 0) {
                    continue;
                }
                d = new Point(w, h, imageRGB[h][w]);
                break;
            }
            if (c != null && d != null && !RobotUtil.isEqualCoordinate(c, d)) {
                fivePoint.setC(c);
                fivePoint.setD(d);
                break;
            }
        }
        return fivePoint;
    }

    @Override
    protected boolean isIgnoreRgbColor(int keyImagePointRgbColor, int ignoreRgbColor) {
        return (keyImagePointRgbColor ^ ignoreRgbColor) == 0;
    }
}
