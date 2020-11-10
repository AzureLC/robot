package com.azurelc.robot.common.utils;

import com.azurelc.robot.common.entity.Coordinate;
import com.azurelc.robot.common.entity.Point;
import com.azurelc.robot.common.robotsingle.RobotSingleFactory;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class RobotUtil {
    private RobotUtil() {
    }

    public static String getAppClassPath() throws URISyntaxException {
        URL resource = RobotUtil.class.getClassLoader().getResource("");
        Path path = Paths.get(resource.toURI());
        return path.toString();
    }

    /**
     * 获取resources下的文件
     *
     * @param filePath resources下文件的路径
     * @return resources下的文件
     * @throws URISyntaxException 异常
     */
    public static File getImageFile(String... filePath) throws URISyntaxException {
        String appClassPath = RobotUtil.getAppClassPath();
        Path path = Paths.get(appClassPath, filePath);
        return path.toFile();
    }

    /**
     * 获取图片RGB颜色矩阵
     *
     * @param bfImage 图片Buff
     * @return RGB颜色矩阵
     */
    public static int[][] getImageGRB(BufferedImage bfImage) {
        int width = bfImage.getWidth();
        int height = bfImage.getHeight();
        int[][] result = new int[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                //使用getRGB(w, h)获取该点的颜色值是ARGB，而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，即bufImg.getRGB(w, h) & 0xFFFFFF。
                result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
            }
        }
        return result;
    }

    /**
     * 获取屏幕指定区域的截图
     *
     * @param startCoordinate 起始坐标
     * @param endCoordinate 终止坐标
     * @return 图片Buff
     * @throws AWTException 异常
     */
    public static BufferedImage getScreenShot(Coordinate startCoordinate, Coordinate endCoordinate) throws AWTException {
        Robot robot = RobotSingleFactory.getRobotSingle();
        Rectangle rectangle = new Rectangle(startCoordinate.getX(), startCoordinate.getY(), endCoordinate.getX(),
                endCoordinate.getY());
        return robot.createScreenCapture(rectangle);
    }

    public static boolean isEqualForFourEndpointRGB(int a, int b, int c, int d) {
        return (a ^ b) == 0 && (b ^ c) == 0 && (c ^ d) == 0;
    }

    public static boolean isEqualCoordinate(Point aPoint, Point bPoint) {
        return (aPoint.getX() ^ bPoint.getX()) == 0 && (aPoint.getY() ^ bPoint.getY()) == 0;
    }

    public static boolean isEffectivePoint(Coordinate coordinate) {
        return coordinate.getX() >= 0 && coordinate.getY() >= 0;
    }
}
