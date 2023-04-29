package com;

/**
 * description: BottomRay 初始化地雷
 * date: 2022/4/5 17:06
 * author: qianxiang
 */

public class BottomRay {

    //存放坐标
    int[] rays = new int[GameUtil.RAY_MAX * 2]; //相邻两个数代表地雷的x和y
    int x, y;
    boolean isPlace = true;

    void newRay() {
        for (int i = 0; i < GameUtil.RAY_MAX * 2; i += 2) {
            x = (int) (Math.random() * GameUtil.MAP_W + 1); // 1-12
            y = (int) (Math.random() * GameUtil.MAP_H + 1);

            //如果已经存在 就不放置
            for (int j = 0; j < i; j += 2) {
                if (x == rays[j] && y == rays[j + 1]) {
                    i -= 2;
                    isPlace = false;
                    break;
                }
            }

            // 不存在，可以放置
            if (isPlace) {
                rays[i] = x;
                rays[i + 1] = y;
            }

            isPlace = true;

        }

        for (int i = 0; i < GameUtil.RAY_MAX * 2; i += 2) {
            GameUtil.DataBottom[rays[i]][rays[i + 1]] = -1;
        }
    }
}

