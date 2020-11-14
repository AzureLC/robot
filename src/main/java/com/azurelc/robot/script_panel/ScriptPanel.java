package com.azurelc.robot.script_panel;

import com.azurelc.robot.common.constant.Constants;
import com.azurelc.robot.common.utils.ThreadPoolUtil;
import com.azurelc.robot.script.small_ghost.SmallGhost;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ScriptPanel {
    public ScriptPanel() {
        init();
    }

    private void init() {
        JButton smallGhost = new JButton("小");
        smallGhost.addActionListener(event -> smallGhostEvent());

        JButton ghostKing = new JButton("王");
        ghostKing.addActionListener(event -> ghostKingEvent());

        JButton shura = new JButton("修");
        shura.addActionListener(event -> shuraEvent());

        JPanel jPanel = new JPanel();
        jPanel.add(smallGhost);
        jPanel.add(ghostKing);
        jPanel.add(shura);

        JFrame jFrame = new JFrame();
        jFrame.add(jPanel);
        jFrame.setSize(162, 64);
        jFrame.setLocation(Constants.SCREEN_WIDTH - 160, 0);
        jFrame.setAlwaysOnTop(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private void smallGhostEvent() {
        System.out.println("提交小鬼事件至线程池前");
        ThreadPoolUtil.getThreadPool().submit(() -> {
            try {
                SmallGhost.getInstance().smallGhostScript();
            } catch (Exception e) {
                System.out.println("线程发生了异常");
                e.printStackTrace();
            }
        });
        System.out.println("提交小鬼事件至线程池后");
    }

    private void ghostKingEvent() {
        ThreadPoolUtil.getThreadPool().submit(() -> {
        });
    }

    private void shuraEvent() {
        ThreadPoolUtil.getThreadPool().submit(() -> {
        });
    }

}
