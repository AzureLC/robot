package com.azurelc.robot.imagehandler;

import com.azurelc.robot.common.entity.Coordinate;
import com.azurelc.robot.common.utils.RobotUtils;
import com.azurelc.robot.imagehandler.findimage.ImageHandlerImpl;
import com.azurelc.robot.imagehandler.findword.WordHandlerImplAbstract;
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;

public class FindImageImpl implements FindImage {

    private FindImageImpl() {
    }

    private static class ImageHandlerSingle {
        private static final FindImage INSTANCE = new FindImageImpl();
    }

    public static FindImage getInstance() {
        return ImageHandlerSingle.INSTANCE;
    }

    @Override
    public Coordinate doFindImage(Coordinate startCoordinate, Coordinate endCoordinate, double matchDegree,
            String... keyImagePathStr) throws URISyntaxException, IOException, AWTException {
        File keyImageFile = RobotUtils.getImageFile(keyImagePathStr);
        BufferedImage keyImageBuff = ImageIO.read(keyImageFile);
        int[][] keyImageRGB = RobotUtils.getImageGRB(keyImageBuff);
        int width = keyImageBuff.getWidth();
        int height = keyImageBuff.getHeight();
        if (RobotUtils.isEqualForFourEndpointRGB(keyImageRGB[0][0], keyImageRGB[0][width - 1],
                keyImageRGB[height - 1][width - 1], keyImageRGB[height - 1][0])) {
            // 如果四角颜色相同，走找字逻辑
            int ignoreRgbColor = keyImageRGB[0][0];
            return WordHandlerImplAbstract.getInstance().findWordHandler(startCoordinate, endCoordinate, keyImageBuff
                    , keyImageRGB, ignoreRgbColor, matchDegree);
        } else {
            // 否则走找图逻辑
            return ImageHandlerImpl.getInstance().findImageHandler(startCoordinate, endCoordinate,
                    keyImageBuff, keyImageRGB, -1, matchDegree);
        }
    }
}
