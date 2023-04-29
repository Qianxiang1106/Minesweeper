package com;

import java.awt.*;

/**
 * description: GameUtil 工具类 存放静态参数、工具方法
 * date: 2022/4/5 15:53
 * author: qianxiang
 */

public class GameUtil {

    static  int RAY_MAX = 11; //地雷个数
    static int MAP_W = 11; //地图的宽
    static int MAP_H = 11; //地图的高
    static int OFFSET = 45; //雷区偏移量
    static int SQUARE_LENGTH = 50; //格子的边长

    //鼠标相关参数
    static int MOUSE_X;
    static int MOUSE_Y;
    static boolean LEFT = false;
    static boolean RIGHT = false;

    //游戏状态 0：游戏中 1：胜利 2：失败
    static int state = 0;

    //底层元素 -1：雷 0：空 1-8：对应的数字
    static int[][] DataBottom = new int[MAP_W + 2][MAP_H + 2];

    //顶层元素 -1：无覆盖 0：覆盖 1：插旗 2：插错旗
    static int[][] DataTop= new int[MAP_W + 2][MAP_H + 2];

    //载入图片
    static Image[] images = new Image[9];
    static {
        for (int i = 0; i < 9; i++) {
            images[i] = Toolkit.getDefaultToolkit().getImage("imgs/" + i +".png");
        }
    }
    static Image boom = Toolkit.getDefaultToolkit().getImage("imgs/boom.png");
    static Image face = Toolkit.getDefaultToolkit().getImage("imgs/face.png");
    static Image flag = Toolkit.getDefaultToolkit().getImage("imgs/flag.png");
    static Image noflag = Toolkit.getDefaultToolkit().getImage("imgs/noflag.png");
    static Image over = Toolkit.getDefaultToolkit().getImage("imgs/over.png");
    static Image real_boom = Toolkit.getDefaultToolkit().getImage("imgs/real_boom.png");
    static Image top = Toolkit.getDefaultToolkit().getImage("imgs/top.png");
    static Image win = Toolkit.getDefaultToolkit().getImage("imgs/win.png");
}
