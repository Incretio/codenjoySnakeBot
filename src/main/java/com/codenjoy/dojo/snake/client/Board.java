package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.AbstractBoard;
import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.Arrays;
import java.util.List;

/**
 * User: oleksandr.baglai
 * Date: 10/2/12
 * Time: 12:07 AM
 */
public class Board extends AbstractBoard<Elements> {
    private int[][] sumField;

    private class PointWithValue extends PointImpl {
        private int value;

        public PointWithValue(int x, int y) {
            super(x, y);
            this.value = value;
        }

        public PointWithValue(Point point) {
            super(point);
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int compareValueTo(PointWithValue o) {
            return this.getValue() - o.getValue();
        }

        public double compareDistanceTo(PointWithValue o, PointWithValue toPoint) {
            return this.distance(toPoint) - o.distance(toPoint);
        }

        @Override
        public String toString(){
            return "X = " + this.getX() + "; Y = " + this.getY();
        }

        public void print(){
            System.out.println(this.toString());
        }
    }

    public void calcSumField() {
        int height = getField().length;
        int width = getField()[0].length;
        sumField = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isAt(x, y,
                        Elements.BREAK,
                        Elements.HEAD_UP,
                        Elements.HEAD_DOWN,
                        Elements.HEAD_LEFT,
                        Elements.HEAD_RIGHT,
                        Elements.TAIL_HORIZONTAL,
                        Elements.TAIL_VERTICAL,
                        Elements.TAIL_LEFT_DOWN,
                        Elements.TAIL_LEFT_UP,
                        Elements.TAIL_RIGHT_DOWN,
                        Elements.TAIL_RIGHT_UP
                )) {
                    sumField[x][y] = 99;
                } else if (isAt(x, y, Elements.BAD_APPLE)) {
                    sumField[x][y] = 50;
                } else if (isAt(x, y, Elements.GOOD_APPLE)) {
                    sumField[x][y] = -1;
                } else {
                    Point apple = getApples().get(0);
                    for (int potentialValue = 0; potentialValue < getField().length; potentialValue++) {
                        boolean checkX1Value = Math.abs(x - apple.getX()) - 1 <= potentialValue;
                        boolean checkY1Value = Math.abs(y - apple.getY()) - 1 == potentialValue;
                        boolean checkX2Value = Math.abs(x - apple.getX()) - 1 == potentialValue;
                        boolean checkY2Value = Math.abs(y - apple.getY()) - 1 <= potentialValue;
                        if ((checkX1Value && checkY1Value) || (checkX2Value && checkY2Value)) {
                            sumField[x][y] = potentialValue;
                        }
                    }
                }
            }

        }
        ClientUtils.printField(sumField);
    }

    public Direction getNextStep() {
        Point head = getHead();
        PointWithValue apple = new PointWithValue(getApples().get(0).getX(), getApples().get(0).getY());
        apple.setValue(sumField[apple.getX()][apple.getY()]);

        PointWithValue upStep = new PointWithValue(head.getX(), head.getY() - 1);
        upStep.setValue(sumField[upStep.getX()][upStep.getY()]);

        PointWithValue downStep = new PointWithValue(head.getX(), head.getY() + 1);
        downStep.setValue(sumField[downStep.getX()][downStep.getY()]);

        PointWithValue leftStep = new PointWithValue(head.getX() - 1, head.getY());
        leftStep.setValue(sumField[leftStep.getX()][leftStep.getY()]);

        PointWithValue rightStep = new PointWithValue(head.getX() + 1, head.getY());
        rightStep.setValue(sumField[rightStep.getX()][rightStep.getY()]);

        PointWithValue nextStepPoint = upStep;

        rightStep.print();
        if (downStep.compareValueTo(nextStepPoint) <= 0) {
            if (downStep.compareValueTo(nextStepPoint) == 0){
                nextStepPoint = (downStep.compareDistanceTo(nextStepPoint, apple) < 0) ? downStep : nextStepPoint;
            } else {
                nextStepPoint = downStep;
            }
            rightStep.print();
        }

        if (leftStep.compareValueTo(nextStepPoint) <= 0) {
            if (leftStep.compareValueTo(nextStepPoint) == 0){
                nextStepPoint = (leftStep.compareDistanceTo(nextStepPoint, apple) < 0) ? leftStep : nextStepPoint;
            } else {
                nextStepPoint = leftStep;
            }
            rightStep.print();
        }

        if (rightStep.compareValueTo(nextStepPoint) <= 0) {
            if (rightStep.compareValueTo(nextStepPoint) == 0){
                nextStepPoint = (rightStep.compareDistanceTo(nextStepPoint, apple) < 0) ? rightStep : nextStepPoint;
            } else {
                nextStepPoint = rightStep;
            }
            rightStep.print();
        }

        nextStepPoint.print();

        if (head.getX() < nextStepPoint.getX()) {
            return Direction.RIGHT;
        }
        if (head.getX() > nextStepPoint.getX()) {
            return Direction.LEFT;
        }
        if (head.getY() > nextStepPoint.getY()) {
            return Direction.UP;
        }
        if (head.getY() < nextStepPoint.getY()) {
            return Direction.DOWN;
        } else {
            return Direction.DOWN;
        }
    }

    @Override
    public Elements valueOf(char ch) {
        return Elements.valueOf(ch);
    }

    public List<Point> getApples() {
        return get(Elements.GOOD_APPLE);
    }

    public Direction getSnakeDirection() {
        Point head = getHead();
        if (head == null) {
            return null;
        }
        if (isAt(head.getX(), head.getY(), Elements.HEAD_LEFT)) {
            return Direction.LEFT;
        } else if (isAt(head.getX(), head.getY(), Elements.HEAD_RIGHT)) {
            return Direction.RIGHT;
        } else if (isAt(head.getX(), head.getY(), Elements.HEAD_UP)) {
            return Direction.UP;
        } else {
            return Direction.DOWN;
        }
    }

    public Point getHead() {
        List<Point> result = get(
                Elements.HEAD_UP,
                Elements.HEAD_DOWN,
                Elements.HEAD_LEFT,
                Elements.HEAD_RIGHT);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public List<Point> getBarriers() {
        List<Point> result = getSnake();
        result.addAll(getStones());
        result.addAll(getWalls());
        return result;
    }

    public List<Point> getSnake() {
        Point head = getHead();
        if (head == null) {
            return Arrays.asList();
        }
        List<Point> result = get(
                Elements.TAIL_END_DOWN,
                Elements.TAIL_END_LEFT,
                Elements.TAIL_END_UP,
                Elements.TAIL_END_RIGHT,
                Elements.TAIL_HORIZONTAL,
                Elements.TAIL_VERTICAL,
                Elements.TAIL_LEFT_DOWN,
                Elements.TAIL_LEFT_UP,
                Elements.TAIL_RIGHT_DOWN,
                Elements.TAIL_RIGHT_UP);
        result.add(0, head);
        return result;
    }

    public boolean isGameOver() {
        return getHead() == null;
    }

    @Override
    public String toString() {
        return String.format("Board:\n%s\n" +
                        "Apple at: %s\n" +
                        "Stones at: %s\n" +
                        "Head at: %s\n" +
                        "Snake at: %s\n" +
                        "Current direction: %s",
                boardAsString(),
                getApples(),
                getStones(),
                getHead(),
                getSnake(),
                getSnakeDirection());
    }

    public List<Point> getStones() {
        return get(Elements.BAD_APPLE);
    }

    public List<Point> getWalls() {
        return get(Elements.BREAK);
    }
}