package com.azurelc.robot.imagehandler.findimage;

import com.azurelc.robot.common.entity.Coordinate;
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public interface ImageHandler {
    Coordinate findImageHandler(Coordinate startCoordinate, Coordinate endCoordinate, BufferedImage keyImageBuff,
            int[][] keyImageRGB, int ignoreRgbColor, double matchDegree) throws URISyntaxException, IOException,
            AWTException;
}
