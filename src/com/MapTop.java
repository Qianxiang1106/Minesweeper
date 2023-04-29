package com;

import java.awt.*;

/**
 * description: MapTop 顶层地图类 绘制顶层组件 构建判断逻辑
 * date: 2022/4/6 21:59
 * author: qianxiang
 */

public class MapTop {

    //格子位置
    int temp_x;
    int temp_y;

    //重置游戏
    void regame() {
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                GameUtil.DataTop[i][j] = 0;
            }
        }
    }

    //判断逻辑
    void logic() {

        temp_x = 0;
        temp_y = 0;
        if (GameUtil.MOUSE_X > GameUtil.OFFSET && GameUtil.MOUSE_Y > 3 * GameUtil.OFFSET) {
            temp_x = (GameUtil.MOUSE_X - GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH + 1;
            temp_y = (GameUtil.MOUSE_Y - 3 * GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH + 1;
        }

        if (temp_x >= 1 && temp_x <= GameUtil.MAP_W && temp_y >= 1 && temp_y <= GameUtil.MAP_H) { //如果在雷区当中
            if (GameUtil.LEFT) {
                if(GameUtil.DataTop[temp_x][temp_y] == 0) {
                    GameUtil.DataTop[temp_x][temp_y] = -1;
                }
                spaceOpen(temp_x, temp_y);
                GameUtil.LEFT = false;
            }
            if (GameUtil.RIGHT) {
                if(GameUtil.DataTop[temp_x][temp_y] == 0) {
                    GameUtil.DataTop[temp_x][temp_y] = 1;
                } else if(GameUtil.DataTop[temp_x][temp_y] == 1) {
                    GameUtil.DataTop[temp_x][temp_y] = 0;
                } else if(GameUtil.DataTop[temp_x][temp_y] == -1) {
                    numOpen(temp_x, temp_y);
                }
                GameUtil.RIGHT = false;
            }
        }
        boom();
        victory();
    }

    void numOpen(int x, int y) {
        int count = 0;
        if (GameUtil.DataBottom[x][y] > 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (GameUtil.DataTop[i][j] == 1) {
                        count++;
                    }
                }
            }
            if (count == GameUtil.DataBottom[x][y]) {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (GameUtil.DataTop[i][j] != 1) {
                            GameUtil.DataTop[i][j] = -1;
                        }
                        if (i >= 1 && i <= GameUtil.MAP_W && j >= 1 && j <= GameUtil.MAP_H) {
                            spaceOpen(i, j);
                        }
                    }
                }
            }
        }
    }

    //失败判定
    boolean boom() {
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.DataBottom[i][j] == -1 && GameUtil.DataTop[i][j] == -1) {
                    GameUtil.state = 2;
                    seeBoom();
                    return true;
                }
            }
        }
        return false;
    }

    //胜利判定
    boolean victory() {
        int count = 0;
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.DataTop[i][j] != -1) {
                    count++;
                }
            }
        }
        if (count == GameUtil.RAY_MAX) {
            GameUtil.state = 1;
            for (int i = 1; i <= GameUtil.MAP_W; i++) {
                for (int j = 1; j <= GameUtil.MAP_H; j++) {
                    if (GameUtil.DataTop[i][j] == 0) {
                        GameUtil.DataTop[i][j] = 1;
                    }
                }
            }
            return true;
        }
        return false;
    }

    //显示所有的雷
    void seeBoom() {
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                //底层是雷 顶层不是旗
                if (GameUtil.DataBottom[i][j] == -1 && GameUtil.DataTop[i][j] != 1) {
                    GameUtil.DataTop[i][j] = -1;
                }
                //底层不是雷 顶层是旗
                if (GameUtil.DataBottom[i][j] != -1 && GameUtil.DataTop[i][j] == 1) {
                    GameUtil.DataTop[i][j] = 2;
                }
            }
        }
    }

    //打开空格
    void spaceOpen(int x, int y) {
        if (GameUtil.DataBottom[x][y] == 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (GameUtil.DataTop[i][j] != -1) {
                        GameUtil.DataTop[i][j] = -1;
                        if (i >= 1 && i <= GameUtil.MAP_W && j >= 1 && j <= GameUtil.MAP_H) {
                            spaceOpen(i, j);
                        }
                    }
                }
            }
        }
    }

    void paintSelf(Graphics g) {
        logic();
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                //覆盖
                if (GameUtil.DataTop[i][j] == 0) {
                    g.drawImage(GameUtil.top,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }

                //插旗
                if (GameUtil.DataTop[i][j] == 1) {
                    g.drawImage(GameUtil.flag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }

                //插错旗
                if (GameUtil.DataTop[i][j] == 2) {
                    g.drawImage(GameUtil.noflag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }
            }
        }
    }
}
