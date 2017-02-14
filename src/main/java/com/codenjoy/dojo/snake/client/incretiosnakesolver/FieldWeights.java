package com.codenjoy.dojo.snake.client.incretiosnakesolver;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.snake.client.Board;
import com.codenjoy.dojo.snake.model.Elements;

/**
 * Класс для вычисления и работы с весом вершины
 */
public class FieldWeights {
    public static final int MAX_WEIGHT = 999999;
    public static final int MIN_WEIGHT = -MAX_WEIGHT;
    public static final int STONE_WEIGHT = 9999;

    private int[][] fieldWeights;
    private Board board;

    public FieldWeights(Board board) {
        this.board = board;
        calcWeights();
    }

    public FieldWeights(int[][] field) {
        this.fieldWeights = Utils.copyArray(field);
    }

    public void setWeight(MovePoint point, int value) {
        fieldWeights[point.getX()][point.getY()] = value;
    }

    public int getWeight(MovePoint point) {
        return fieldWeights[point.getX()][point.getY()];
    }

    public FieldWeights clone() {
        return new FieldWeights(this.fieldWeights);
    }

    private void calcWeights() {
        fieldWeights = new int[getBoardHeight()][getBoardWidth()];

        for (int y = 0; y < getBoardHeight(); y++) {
            for (int x = 0; x < getBoardWidth(); x++) {
                setWeight(x, y);
            }
        }

        //Utils.print(fieldWeights);
    }

    private int getBoardWidth() {
        return board.getField()[0].length;
    }

    private int getBoardHeight() {
        return board.getField().length;
    }

    private void setWeight(int x, int y) {
        if (isDeadEnd(x, y)) {
            fieldWeights[x][y] = MAX_WEIGHT;
        } else if (isStone(x, y)) {
            fieldWeights[x][y] = STONE_WEIGHT;
        } else if (isApple(x, y)) {
            fieldWeights[x][y] = MIN_WEIGHT;
        } else {
            fieldWeights[x][y] = getWeightNearApple(x, y);
        }
    }

    private boolean isDeadEnd(int x, int y) {
        return board.isAt(x, y,
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
        );
    }

    private boolean isStone(int x, int y) {
        return board.isAt(x, y, Elements.BAD_APPLE);
    }

    private boolean isApple(int x, int y) {
        return board.isAt(x, y, Elements.GOOD_APPLE);
    }

    private int getWeightNearApple(int x, int y) {
        Point apple = board.getApples().get(0);
        return Math.abs(apple.getX() - x) + Math.abs(apple.getY() - y);
    }
}
