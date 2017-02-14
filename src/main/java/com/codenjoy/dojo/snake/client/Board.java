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

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: oleksandr.baglai
 * Date: 10/2/12
 * Time: 12:07 AM
 */
public class Board extends AbstractBoard<Elements> {
    private final int MAX_VALUE = 999999;
    private int MAX_SNAKE_LENGTH = 1000;
    public int REMAINING_STEPS = 9;

    public Board() {
    }

    private int[][] sumField;

    public void calcSumField() {
        System.out.println("calc");
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
                        //Elements.TAIL_END_UP,
                        //Elements.TAIL_END_DOWN,
                        //Elements.TAIL_END_LEFT,
                        //Elements.TAIL_END_RIGHT,
                        Elements.TAIL_HORIZONTAL,
                        Elements.TAIL_VERTICAL,
                        Elements.TAIL_LEFT_DOWN,
                        Elements.TAIL_LEFT_UP,
                        Elements.TAIL_RIGHT_DOWN,
                        Elements.TAIL_RIGHT_UP
                )) {
                    sumField[x][y] = MAX_VALUE;
                } else if (isAt(x, y, Elements.BAD_APPLE)) {
                    if (getSnake().size() > MAX_SNAKE_LENGTH) {
                        sumField[x][y] = -MAX_VALUE;
                    } else {
                        sumField[x][y] = 50;
                    }

                } else if (isAt(x, y, Elements.GOOD_APPLE)) {
                    if (getSnake().size() > MAX_SNAKE_LENGTH) {
                        sumField[x][y] = 50;
                    } else {
                        sumField[x][y] = -MAX_VALUE;
                    }
                } else {
                    Point apple;
                    if (getSnake().size() > MAX_SNAKE_LENGTH) {
                        apple = getStones().get(0);
                    } else {
                        apple = getApples().get(0);
                    }
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
    }


    static class SnakePath implements Comparable<SnakePath> {
        public String direction;
        public int stepSum;
        public int stepToEaten;

        public SnakePath(String direction, int stepSum, int stepToEaten) {
            this.direction = direction;
            this.stepSum = stepSum;
            this.stepToEaten = stepToEaten;
        }

        @Override
        public String toString() {
            return "direction = " + direction + ", stepSum = " + stepSum + ", stepToEaten = " + stepToEaten;
        }

        @Override
        public int compareTo(SnakePath o) {
            if (stepToEaten != o.stepToEaten) {
                return stepToEaten - o.stepToEaten;
            } else {
                return stepSum - o.stepSum;
            }
        }

        public static void main(String[] args) {
            List<SnakePath> list = new ArrayList<>();

            list.add(new SnakePath("", -99982, 11));
            list.add(new SnakePath("", Integer.MAX_VALUE, Integer.MAX_VALUE));

            System.out.println(list.get(0).compareTo(list.get(1)));
        }
    }

    public int[][] copyArray(int[][] array) {
        int[][] copy = new int[array.length][array[0].length]; // new top-level array of same size
        for (
                int i = 0;
                i < array.length; i++)

        {
            copy[i] = array[i].clone();
        }
        return copy;
    }

    public Direction getNextStep(final int remainingSteps) {
        System.out.println("goNextStep");
        Point head = getHead();
        final int x = head.getX();
        final int y = head.getY();
        Point apple;
        if (getSnake().size() > MAX_SNAKE_LENGTH) {
            apple = getStones().get(0);
        } else {
            apple = getApples().get(0);
        }

        final List<SnakePath> snakePaths = Collections.synchronizedList(new ArrayList<SnakePath>());
        final List<SnakePath> snakePathsLeft = Collections.synchronizedList(new ArrayList<SnakePath>());
        final List<SnakePath> snakePathsUp = Collections.synchronizedList(new ArrayList<SnakePath>());
        final List<SnakePath> snakePathsRight = Collections.synchronizedList(new ArrayList<SnakePath>());
        final List<SnakePath> snakePathsDown = Collections.synchronizedList(new ArrayList<SnakePath>());


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                getNextStep(copyArray(sumField), snakePathsLeft, x - 1, y, x, y, 1, 0, -1, "LEFT", remainingSteps);
                System.out.println("thread1 done");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                getNextStep(copyArray(sumField), snakePathsUp, x, y - 1, x, y, 1, 0, -1, "UP", remainingSteps);
                System.out.println("thread2 done");
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                getNextStep(copyArray(sumField), snakePathsRight, x + 1, y, x, y, 1, 0, -1, "RIGHT", remainingSteps);
                System.out.println("thread3 done");
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                getNextStep(copyArray(sumField), snakePathsDown, x, y + 1, x, y, 1, 0, -1, "DOWN", remainingSteps);
                System.out.println("thread4 done");
            }
        });

        Date startDate = new Date();


//        if (this.getSnake().size() / 2 < countOfSpace(x - 1, y, copyArray(sumField), 0)) {
//            thread1.start();
//        }
//        if (this.getSnake().size() / 2 < countOfSpace(x, y - 1, copyArray(sumField), 0)) {
//            thread2.start();
//        }
//        if (this.getSnake().size() / 2 < countOfSpace(x + 1, y, copyArray(sumField), 0)) {
//            thread3.start();
//        }
//        if (this.getSnake().size() / 2 < countOfSpace(x, y + 1, copyArray(sumField), 0)) {
//            thread4.start();
//        }

        //ExecutorService service = Executors.newFixedThreadPool(4);
        if (isSeeTail(x - 1, y, copyArray(sumField))) {
            //service.submit(thread1);
            thread1.start();
        }
        if (isSeeTail(x, y - 1, copyArray(sumField))) {
            //service.submit(thread2);
            thread2.start();
        }
        if (isSeeTail(x + 1, y, copyArray(sumField))) {
            //service.submit(thread3);
            thread3.start();
        }
        if (isSeeTail(x, y + 1, copyArray(sumField))) {
            //service.submit(thread4);
            thread4.start();
        }

        /*try {
            service.wait(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        snakePaths.addAll(snakePathsLeft);
        snakePaths.addAll(snakePathsUp);
        snakePaths.addAll(snakePathsRight);
        snakePaths.addAll(snakePathsDown);
        if (snakePaths.size() == 0) {
            return getNextStep(remainingSteps - 1);
        }

        System.out.println("time = " + (new Date().getTime() - startDate.getTime()));

        System.out.println("size = " + snakePaths.size());
        SnakePath minSnakePath = Collections.min(snakePaths);
        System.out.println(minSnakePath);
        String direction = minSnakePath.direction;

        if (direction.equals("LEFT")) {
            return Direction.LEFT;
        }
        if (direction.equals("UP")) {
            return Direction.UP;
        }
        if (direction.equals("RIGHT")) {
            return Direction.RIGHT;
        }
        if (direction.equals("DOWN")) {
            return Direction.DOWN;
        } else {
            if (remainingSteps < 1) {
                return Direction.DOWN;
            } else {
                return getNextStep(remainingSteps - 1);

            }
        }

    }

    public List<SnakePath> getNextStep(
            int[][] curSumField,
            List<SnakePath> sums,
            int newX, int newY,
            int oldX, int oldY,
            int stepCount, int stepSum, int stepToEaten,
            String direction, int remainingSteps) {
        if (curSumField[newX][newY] == MAX_VALUE || newX <= 0 || newX >= curSumField.length - 1 || newY <= 0 || newY >= curSumField.length - 1) {
            return sums;
        }

        int newStepSum = curSumField[newX][newY] + stepSum;
        if (stepCount > remainingSteps) {
            sums.add(new SnakePath(direction, newStepSum, (stepToEaten < 0) ? MAX_VALUE : stepToEaten));
            return sums;
        }

        int curFieldValue = curSumField[newX][newY];
        curSumField[newX][newY] = MAX_VALUE;


        // условие нужно, но с ним долго
        /*if (getSnake().size() > MAX_SNAKE_LENGTH) {
            if ((stepToEaten < 0) && (this.getStones().get(0).getX() == newX) && (this.getStones().get(0).getY() == newY)) {
                stepToEaten = stepCount;
            }
        } else */
        {
            if ((stepToEaten < 0) && (this.getApples().get(0).getX() == newX) && (this.getApples().get(0).getY() == newY)) {
                stepToEaten = stepCount;
            }
        }


        int nextStepCount = stepCount + 1;


//        if (!((oldX == newX - 1) && (oldY == newY))) {
//            if (stepCount < 3 || this.getSnake().size() / 2 < countOfSpace(newX - 1, newY, copyArray(curSumField), 0)) {
//                getNextStep(curSumField, sums, newX - 1, newY, newX, newY, nextStepCount, newStepSum, stepToEaten, direction);
//            }
//        }
//        if (!((oldX == newX) && (oldY == newY - 1))) {
//            if (stepCount < 3 || this.getSnake().size() / 2 < countOfSpace(newX, newY - 1, copyArray(curSumField), 0)) {
//                getNextStep(curSumField, sums, newX, newY - 1, newX, newY, nextStepCount, newStepSum, stepToEaten, direction);
//            }
//        }
//        if (!((oldX == newX + 1) && (oldY == newY))) {
//            if (stepCount < 3 || this.getSnake().size() / 2 < countOfSpace(newX + 1, newY, copyArray(curSumField), 0)) {
//                getNextStep(curSumField, sums, newX + 1, newY, newX, newY, nextStepCount, newStepSum, stepToEaten, direction);
//            }
//        }
//        if (!((oldX == newX) && (oldY == newY + 1))) {
//            if (stepCount < 3 || this.getSnake().size() / 2 < countOfSpace(newX, newY + 1, copyArray(curSumField), 0)) {
//                getNextStep(curSumField, sums, newX, newY + 1, newX, newY, nextStepCount, newStepSum, stepToEaten, direction);
//            }
//        }

        if (!((oldX == newX - 1) && (oldY == newY))) {
            if (isSeeTail(newX - 1, newY, copyArray(curSumField))) {
                getNextStep(curSumField, sums, newX - 1, newY, newX, newY, nextStepCount, newStepSum, stepToEaten, direction, remainingSteps);
            }
        }
        if (!((oldX == newX) && (oldY == newY - 1))) {
            if (isSeeTail(newX, newY - 1, copyArray(curSumField))) {
                getNextStep(curSumField, sums, newX, newY - 1, newX, newY, nextStepCount, newStepSum, stepToEaten, direction, remainingSteps);
            }
        }
        if (!((oldX == newX + 1) && (oldY == newY))) {
            if (isSeeTail(newX + 1, newY, copyArray(curSumField))) {
                getNextStep(curSumField, sums, newX + 1, newY, newX, newY, nextStepCount, newStepSum, stepToEaten, direction, remainingSteps);
            }
        }
        if (!((oldX == newX) && (oldY == newY + 1))) {
            if (isSeeTail(newX, newY + 1, copyArray(curSumField))) {
                getNextStep(curSumField, sums, newX, newY + 1, newX, newY, nextStepCount, newStepSum, stepToEaten, direction, remainingSteps);
            }
        }

        curSumField[newX][newY] = curFieldValue;

        return sums;
    }

    public boolean isSeeTail(int curX, int curY, int[][] field) {
        if (isAt(curX, curY,
                Elements.TAIL_END_DOWN,
                Elements.TAIL_END_LEFT,
                Elements.TAIL_END_UP,
                Elements.TAIL_END_RIGHT)) {
            return true;
        } else if (field[curX][curY] == MAX_VALUE /*|| count > getSnake().size()*/) {
            return false;
        }

        field[curX][curY] = MAX_VALUE;
        boolean result;
        result = isSeeTail(curX - 1, curY, field);
        if (result) {
            return true;
        }
        result = isSeeTail(curX + 1, curY, field);
        if (result) {
            return true;
        }
        result = isSeeTail(curX, curY - 1, field);
        if (result) {
            return true;
        }
        result = isSeeTail(curX, curY + 1, field);
        if (result) {
            return true;
        }
        return false;
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