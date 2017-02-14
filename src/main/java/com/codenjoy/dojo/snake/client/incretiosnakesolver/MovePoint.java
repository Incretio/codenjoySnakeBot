package com.codenjoy.dojo.snake.client.incretiosnakesolver;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

/**
 * Класс
 */
class MovePoint extends PointImpl {

    public MovePoint(int x, int y) {
        super(x, y);
    }

    public MovePoint(Point point) {
        super(point);
    }

    public MovePoint getLeftPoint(){
        return new MovePoint(x - 1, y);
    }

    public MovePoint getUpPoint(){
        return new MovePoint(x, y - 1);
    }

    public MovePoint getRightPoint(){
        return new MovePoint(x + 1, y);
    }

    public MovePoint getDownPoint(){
        return new MovePoint(x, y + 1);
    }
}
