package com;

/**
 * description: BottomNum
 * date: 2022/4/5 18:29
 * author: qianxiang
 */

public class BottomNum {

    void newNum() {
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.DataBottom[i][j] == -1) {
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (GameUtil.DataBottom[k][l] >= 0) {
                                GameUtil.DataBottom[k][l]++;
                            }
                        }
                    }
                }
            }
        }
    }
}
