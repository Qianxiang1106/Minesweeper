package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * description: Gamewin
 * date: 2022/4/5 15:33
 * author: qianxiang
 */

public class Gamewin extends JFrame {

    int width = 2 * GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH;
    int height = 4 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH;

    Image offScreenImage = null;

    MapButton mapButton = new MapButton();
    MapTop mapTop = new MapTop();

    void launch() throws InterruptedException {
        this.setVisible(true); //是否可见
        this.setSize(width, height); //窗口大小
        this.setLocationRelativeTo(null); //窗口位置：居中
        this.setTitle("扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //退出方式

        //鼠标事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (GameUtil.state) {
                    case 0:
                        if (e.getButton() == 1) {
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.LEFT = true;
                        }
                        if (e.getButton() == 3) {
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.RIGHT = true;
                        }
                        break;
                    case 1: break;
                    case 2:
                        if (e.getButton() == 1) {
                            if (e.getX() > GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W / 2)
                            && e.getX() < GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W / 2 + 1)
                            && e.getY() > GameUtil.OFFSET && e.getY() < GameUtil.OFFSET + GameUtil.SQUARE_LENGTH) {
                                mapButton.regame();
                                mapTop.regame();
                                GameUtil.state = 0;
                            }
                        }
                        break;
                    default:
                }

            }
        });

        while (true) {
            repaint();
            try {
                Thread.sleep(100); //每一百毫秒刷新一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        offScreenImage = this.createImage(width, height);
        Graphics gImage = offScreenImage.getGraphics();

        //设置背景颜色
        gImage.setColor(Color.lightGray);
        gImage.fillRect(0, 0, width, height); //将颜色填充到整个画布上去

        mapButton.paintSelf(gImage);
        mapTop.paintSelf(gImage);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) throws InterruptedException {
        Gamewin gamewin = new Gamewin();
        gamewin.launch();
    }
}


