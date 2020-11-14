package com.azurelc.robot.script.small_ghost;

import com.azurelc.robot.common.constant.Constants;
import com.azurelc.robot.common.entity.Coordinate;
import com.azurelc.robot.common.exception.RobotScriptException;
import com.azurelc.robot.common.robotsingle.RobotSingleFactory;
import com.azurelc.robot.common.utils.RobotUtil;
import com.azurelc.robot.imagehandler.FindImageImpl;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

public class SmallGhost {

    private Robot robot = RobotSingleFactory.getRobotSingle();

    private SmallGhost() {
    }

    private static class SmallGhostSingle {
        private static final SmallGhost INSTANCE = new SmallGhost();
    }

    public static SmallGhost getInstance() {
        return SmallGhostSingle.INSTANCE;
    }

    public void smallGhostScript() {
        System.out.println("运行了小鬼点击事件");
        robot.delay(2000);
        Random random = new Random(31);
        int randomNumber = random.nextInt(180);
        do {
            System.out.println("进了do{}while()方法");
            // 点自动次数
            int autoTimes = 20;
            for (int i = 0; i < autoTimes; i++) {
                robot.delay(2000);
                // 领任务
                lingRenWu(random);

                // 寻路
                xulu(random);

                // 进入战斗，结束战斗，用符
                jinRuZhanDou(random, i == autoTimes - 1);
            }

        } while (true);
    }

    private void lingRenWu(Random random) throws RobotScriptException {
        try {
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_5);
            robot.keyRelease(KeyEvent.VK_5);
            robot.keyRelease(KeyEvent.VK_ALT);
            System.out.println("按了Alt+5");

            robot.delay(300 + random.nextInt(500));

            // TODO 钟馗
            Coordinate coordinateZK;
            int findImageTimesZK = 0;
            do {
                coordinateZK = FindImageImpl.getInstance().doFindImage(new Coordinate(0, 0), new Coordinate(1036,
                        783), 0.9, Constants.ZK_I_P_SG);
                findImageTimesZK++;
                robot.delay(500);
                if (findImageTimesZK >= 64) {
                    throw new RobotScriptException("5 Times not find Image ! path:" + Constants.ZK_I_P_SG);
                }
            } while (!RobotUtil.isEffectivePoint(coordinateZK));
            System.out.println("找图结束");
            robot.delay(300 + random.nextInt(500));
            if (RobotUtil.isEffectivePoint(coordinateZK)) {
                System.out.println("移动鼠标");
                // 鼠标上移30点
                robot.mouseMove(coordinateZK.getX(), coordinateZK.getY() - 30);
                robot.delay(500 + random.nextInt(500));
                // 按下鼠标左键
                robot.mousePress(KeyEvent.BUTTON1_MASK);
                robot.mouseRelease(KeyEvent.BUTTON1_MASK);

                robot.delay(500 + random.nextInt(500));

                // TODO 我来帮你
                Coordinate coordinateBN;
                int findImageTimesBN = 0;
                do {
                    coordinateBN = FindImageImpl.getInstance().doFindImage(new Coordinate(0, 0), new Coordinate(1036,
                            783), 0.9, Constants.BN_I_P_SG);
                    findImageTimesBN++;
                    robot.delay(500);
                    if (findImageTimesBN >= 64) {
                        throw new RobotScriptException("5 Times not find Image ! path:" + Constants.BN_I_P_SG);
                    }
                } while (!RobotUtil.isEffectivePoint(coordinateBN));
                robot.delay(300 + random.nextInt(500));
                if (RobotUtil.isEffectivePoint(coordinateBN)) {
                    System.out.println("移动鼠标");
                    robot.mouseMove(coordinateBN.getX(), coordinateBN.getY());
                    robot.delay(500 + random.nextInt(500));
                    // 按下鼠标左键
                    robot.mousePress(KeyEvent.BUTTON1_MASK);
                    robot.mouseRelease(KeyEvent.BUTTON1_MASK);

                    // TODO 各位且去
                    Coordinate coordinateQQ;
                    int findImageTimesQQ = 0;
                    do {
                        coordinateQQ = FindImageImpl.getInstance().doFindImage(new Coordinate(0, 0),
                                new Coordinate(1036, 783), 0.9, Constants.QQ_I_P_SG);
                        findImageTimesQQ++;
                        robot.delay(500);
                        if (findImageTimesQQ >= 64) {
                            throw new RobotScriptException("5 Times not find Image ! path:" + Constants.QQ_I_P_SG);
                        }
                    } while (!RobotUtil.isEffectivePoint(coordinateQQ));
                    if (RobotUtil.isEffectivePoint(coordinateQQ)) {
                        System.out.println("移动鼠标");
                        robot.mouseMove(coordinateQQ.getX(), coordinateQQ.getY());
                        robot.delay(500 + random.nextInt(500));
                        // 按下鼠标左键
                        robot.mousePress(KeyEvent.BUTTON1_MASK);
                        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                    }
                }
            }

            robot.delay(1500);
        } catch (URISyntaxException | IOException | AWTException e) {
            e.printStackTrace();
        }
    }


    private boolean xulu(Random random) throws RobotScriptException {
        try {
            // TODO 鬼
            Coordinate coordinateGU;
            int findImageTimes = 0;
            do {
                coordinateGU = FindImageImpl.getInstance().doFindImage(new Coordinate(4, 175), new Coordinate(200,
                        520), 0.9, Constants.GU_I_P_SG);
                findImageTimes++;
                robot.delay(500);
                if (findImageTimes >= 64) {
                    throw new RobotScriptException("5 Times not find Image ! path:" + Constants.GU_I_P_SG);
                }
            } while (!RobotUtil.isEffectivePoint(coordinateGU));
            robot.delay(300 + random.nextInt(500));
            if (RobotUtil.isEffectivePoint(coordinateGU)) {
                System.out.println("移动鼠标");
                robot.mouseMove(4 + coordinateGU.getX(), 175 + coordinateGU.getY());
                robot.delay(500 + random.nextInt(500));
                // 按下鼠标左键
                robot.mousePress(KeyEvent.BUTTON1_MASK);
                robot.mouseRelease(KeyEvent.BUTTON1_MASK);

                // 等待寻路30s
                robot.delay(30000);

                // TODO 妖孽受死
                Coordinate coordinateSB;
                do {
                    coordinateSB = FindImageImpl.getInstance().doFindImage(new Coordinate(0, 0), new Coordinate(1036,
                            783), 0.9, Constants.SB_I_P_SG);
                    robot.delay(500);
                } while (!RobotUtil.isEffectivePoint(coordinateSB));
                if (RobotUtil.isEffectivePoint(coordinateSB)) {
                    robot.mouseMove(coordinateSB.getX(), coordinateSB.getY());
                    robot.delay(500 + random.nextInt(500));
                }
            }
            robot.delay(1500);
        } catch (URISyntaxException | IOException | AWTException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void jinRuZhanDou(Random random, boolean isAuto) throws RobotScriptException {
        // 按下鼠标左键
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);

        // 5s后轮回自动
        robot.delay(5 * 1000);
        if (isAuto) {
            for (int i = 0; i < 5; i++) {
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_8);
                robot.keyRelease(KeyEvent.VK_8);
                robot.keyRelease(KeyEvent.VK_ALT);

                robot.delay(700 + random.nextInt(300));

                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_CONTROL);

                robot.delay(700 + random.nextInt(300));
            }
        }

        try {
            // TODO 符
            Coordinate coordinateFU;
            do {
                coordinateFU = FindImageImpl.getInstance().doFindImage(new Coordinate(950, 500), new Coordinate(1036,
                        700), 0.9, Constants.FU_I_P_SG);
                robot.delay(500);
            } while (!RobotUtil.isEffectivePoint(coordinateFU));
            if (RobotUtil.isEffectivePoint(coordinateFU)) {
                robot.mouseMove(950 + coordinateFU.getX(), 500 + coordinateFU.getY());
                robot.delay(500 + random.nextInt(500));

                // 按下鼠标左键
                robot.mousePress(KeyEvent.BUTTON1_MASK);
                robot.mouseRelease(KeyEvent.BUTTON1_MASK);
            }
            robot.delay(1500);
        } catch (URISyntaxException | IOException | AWTException e) {
            e.printStackTrace();
        }
    }
}
