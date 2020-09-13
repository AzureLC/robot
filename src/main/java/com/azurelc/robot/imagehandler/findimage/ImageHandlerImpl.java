package com.azurelc.robot.imagehandler.findimage;

import com.azurelc.robot.common.entity.Coordinate;
import com.azurelc.robot.common.entity.FivePoint;
import com.azurelc.robot.common.entity.Point;
import com.azurelc.robot.imagehandler.AbstractImageHandler;
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public class ImageHandlerImpl extends AbstractImageHandler implements ImageHandler {
    private ImageHandlerImpl() {
    }

    private static class ImageHandlerSingle {
        private static final ImageHandler INSTANCE = new ImageHandlerImpl();
    }

    public static ImageHandler getInstance() {
        return ImageHandlerSingle.INSTANCE;
    }

    @Override
    public Coordinate findImageHandler(Coordinate startCoordinate, Coordinate endCoordinate,
            BufferedImage keyImageBuff, int[][] keyImageRGB, int ignoreRgbColor, double matchDegree) throws URISyntaxException, IOException, AWTException {
        return doFindImage(startCoordinate, endCoordinate, keyImageBuff, keyImageRGB, ignoreRgbColor, matchDegree);
    }

    @Override
    protected FivePoint findFivePoint(BufferedImage bfImage, int[][] imageRGB, int ignoreRgbColor) {
        FivePoint fivePoint = new FivePoint();
        int height = bfImage.getHeight();
        int width = bfImage.getWidth();
        Point a = new Point(0, 0, imageRGB[0][0]);
        fivePoint.setA(a);
        Point b = new Point(width - 1, 0, imageRGB[0][width - 1]);
        fivePoint.setB(b);
        Point c = new Point(0, height - 1, imageRGB[height - 1][0]);
        fivePoint.setC(c);
        Point d = new Point(width - 1, height - 1, imageRGB[height - 1][width - 1]);
        fivePoint.setD(d);
        return fivePoint;
    }

    @Override
    protected boolean isIgnoreRgbColor(int keyImagePointRgbColor, int ignoreRgbColor) {
        return false;
    }
}
