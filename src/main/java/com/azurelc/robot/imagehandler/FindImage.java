package com.azurelc.robot.imagehandler;

import com.azurelc.robot.common.entity.Coordinate;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface FindImage {
    Coordinate doFindImage(Coordinate startCoordinate, Coordinate endCoordinate, double matchDegree,
            String keyImagePathStr) throws URISyntaxException, IOException, AWTException;
}
