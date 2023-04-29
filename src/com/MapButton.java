package com;

import java.awt.*;

/**
 * description: MapButton 底层地图 绘制游戏相关组件
 * date: 2022/4/5 15:45
 * author: qianxiang
 */

public class MapButton {

    BottomRay bottomRay = new BottomRay();
    BottomNum bottomNum = new BottomNum();

    {
        bottomRay.newRay();
        bottomNum.newNum();
    }

    //重置游戏
    void regame() {
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                GameUtil.DataBottom[i][j] = 0;
            }
        }
        bottomRay.newRay();
        bottomNum.newNum();
    }

    void paintSelf(Graphics g) {

        g.setColor(Color.blue);
        
        //画竖线
        for (int i = 0; i <= GameUtil.MAP_W; i++) {
            g.drawLine(GameUtil.OFFSET + i * GameUtil.SQUARE_LENGTH,
                    3 * GameUtil.OFFSET,
                    GameUtil.OFFSET + i * GameUtil.SQUARE_LENGTH,
                    3 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH);
        }

        //画横线
        for (int i = 0; i <= GameUtil.MAP_H; i++) {
            g.drawLine(GameUtil.OFFSET,
                    3 * GameUtil.OFFSET + i * GameUtil.SQUARE_LENGTH,
                    GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                    3 * GameUtil.OFFSET + i * GameUtil.SQUARE_LENGTH);
        }

        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.DataBottom[i][j] >= 0) {
                    g.drawImage(GameUtil.images[GameUtil.DataBottom[i][j]],
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }
            }
        }

        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.DataBottom[i][j] == -1) {
                    g.drawImage(GameUtil.boom,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }
            }
        }

        switch (GameUtil.state) {
            case 0:
                g.drawImage(GameUtil.face, GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W / 2), GameUtil.OFFSET,
                        GameUtil.SQUARE_LENGTH, GameUtil.SQUARE_LENGTH, null);
                break;
            case 1:
                g.drawImage(GameUtil.win, GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W / 2), GameUtil.OFFSET,
                        GameUtil.SQUARE_LENGTH, GameUtil.SQUARE_LENGTH,null);
                break;
            case 2:
                g.drawImage(GameUtil.over, GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W / 2), GameUtil.OFFSET,
                        GameUtil.SQUARE_LENGTH, GameUtil.SQUARE_LENGTH,null);
                break;
            default:
        }
    }

}
