package com.codenjoy.dojo.snake.client.incretiosnakesolver;

import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.snake.client.Board;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class IncretioSnakeSolver {
    private final Board board;
    private final FieldWeights fieldWeights;

    public static int remainingStepsDefault = 8;
    private final int STEP_TO_EATEN_START = -1;
    private final int STEP_COUNT_START = 1;
    private final int STEP_SUM_START = 0;

    public IncretioSnakeSolver(Board board) {
        this(board, new FieldWeights(board));
    }

    public IncretioSnakeSolver(Board board, FieldWeights fieldWeights) {
        this.board = board;
        this.fieldWeights = fieldWeights;
    }

    public Direction getNextStep() {
        return getNextStep(remainingStepsDefault);
    }

    public Direction getNextStep(final int remainingSteps) {
        final MovePoint head = new MovePoint(board.getHead());

        final List<SnakePath> snakePaths = new ArrayList<>();
        final List<SnakePath> snakePathsLeft = new ArrayList<>();
        final List<SnakePath> snakePathsUp = new ArrayList<>();
        final List<SnakePath> snakePathsRight = new ArrayList<>();
        final List<SnakePath> snakePathsDown = new ArrayList<>();


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                fillSnakePaths(fieldWeights.clone(), snakePathsLeft, head.getLeftPoint(), head, STEP_COUNT_START, STEP_SUM_START, STEP_TO_EATEN_START, "LEFT", remainingSteps);
                System.out.println("thread1 done");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                fillSnakePaths(fieldWeights.clone(), snakePathsUp, head.getUpPoint(), head, STEP_COUNT_START, STEP_SUM_START, STEP_TO_EATEN_START, "UP", remainingSteps);
                System.out.println("thread2 done");
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                fillSnakePaths(fieldWeights.clone(), snakePathsRight, head.getRightPoint(), head, STEP_COUNT_START, STEP_SUM_START, STEP_TO_EATEN_START, "RIGHT", remainingSteps);
                System.out.println("thread3 done");
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                fillSnakePaths(fieldWeights.clone(), snakePathsDown, head.getDownPoint(), head, STEP_COUNT_START, STEP_SUM_START, STEP_TO_EATEN_START, "DOWN", remainingSteps);
                System.out.println("thread4 done");
            }
        });

        Date startDate = new Date();

        if (isSeeTail(head.getLeftPoint(), fieldWeights.clone())) {
            thread1.start();
        }
        if (isSeeTail(head.getUpPoint(), fieldWeights.clone())) {
            thread2.start();
        }
        if (isSeeTail(head.getRightPoint(), fieldWeights.clone())) {
            thread3.start();
        }
        if (isSeeTail(head.getDownPoint(), fieldWeights.clone())) {
            thread4.start();
        }


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

        long timePassed = new Date().getTime() - startDate.getTime();
        if (board.getSnake().size() > 100){
            remainingStepsDefault = 30;
        }
//        if (timePassed > 200){
//            remainingStepsDefault--;
//            System.out.println("dec remainingStepsDefault = " + remainingStepsDefault);
//        } else if (timePassed < 100){
//            remainingStepsDefault++;
//            System.out.println("inc remainingStepsDefault = " + remainingStepsDefault);
//        }
        System.out.println("time = " + timePassed);

        System.out.println("size = " + snakePaths.size());
        SnakePath minSnakePath = Collections.min(snakePaths);
        System.out.println(minSnakePath);
        String direction = minSnakePath.direction;


        switch (direction.toString().toUpperCase()) {
            case "LEFT":
                return Direction.LEFT;
            case "UP":
                return Direction.UP;
            case "RIGHT":
                return Direction.RIGHT;
            case "DOWN":
                return Direction.DOWN;
            default:
                if (remainingSteps < 1) {
                    return Direction.DOWN;
                } else {
                    return getNextStep(remainingSteps - 1);
                }
        }

    }

    private List<SnakePath> fillSnakePaths(
            FieldWeights fieldWeights,
            List<SnakePath> sums,
            MovePoint newPoint,
            MovePoint oldPoint,
            int stepCount, int stepSum, int stepToEaten,
            String direction, int remainingSteps) {
        if (fieldWeights.getWeight(newPoint) == FieldWeights.MAX_WEIGHT || newPoint.getX() <= 0 || newPoint.getX() >= board.size() - 1 || newPoint.getY() <= 0 || newPoint.getY() >= board.size() - 1) {
            return sums;
        }

        int newStepSum = fieldWeights.getWeight(newPoint) + stepSum;
        if (stepCount > remainingSteps) {
            sums.add(new SnakePath(direction, newStepSum, (stepToEaten < 0) ? FieldWeights.MAX_WEIGHT : stepToEaten));
            return sums;
        }

        int curFieldValue = fieldWeights.getWeight(newPoint);
        fieldWeights.setWeight(newPoint, FieldWeights.MAX_WEIGHT);

        if ((stepToEaten < 0) && (board.getApples().get(0).getX() == newPoint.getX()) && (board.getApples().get(0).getY() == newPoint.getY())) {
            stepToEaten = stepCount;
        }

        int nextStepCount = stepCount + 1;


        if (!((oldPoint.getX() == newPoint.getX() - 1) && (oldPoint.getY() == newPoint.getY()))) {
            if (isSeeTail(newPoint.getLeftPoint(), fieldWeights.clone())) {
                fillSnakePaths(fieldWeights, sums, newPoint.getLeftPoint(), newPoint, nextStepCount, newStepSum, stepToEaten, direction, remainingSteps);
            }
        }
        if (!((oldPoint.getX() == newPoint.getX()) && (oldPoint.getY() == newPoint.getY() - 1))) {
            if (isSeeTail(newPoint.getUpPoint(), fieldWeights.clone())) {
                fillSnakePaths(fieldWeights, sums, newPoint.getUpPoint(), newPoint, nextStepCount, newStepSum, stepToEaten, direction, remainingSteps);
            }
        }
        if (!((oldPoint.getX() == newPoint.getX() + 1) && (oldPoint.getY() == newPoint.getY()))) {
            if (isSeeTail(newPoint.getRightPoint(), fieldWeights.clone())) {
                fillSnakePaths(fieldWeights, sums, newPoint.getRightPoint(), newPoint, nextStepCount, newStepSum, stepToEaten, direction, remainingSteps);
            }
        }
        if (!((oldPoint.getX() == newPoint.getX()) && (oldPoint.getY() == newPoint.getY() + 1))) {
            if (isSeeTail(newPoint.getDownPoint(), fieldWeights.clone())) {
                fillSnakePaths(fieldWeights, sums, newPoint.getDownPoint(), newPoint, nextStepCount, newStepSum, stepToEaten, direction, remainingSteps);
            }
        }

        fieldWeights.setWeight(newPoint, curFieldValue);

        return sums;
    }

    private boolean isSeeTail(MovePoint point, FieldWeights fieldWeights) {
        if (board.isAt(point.getX(), point.getY(),
                Elements.TAIL_END_DOWN,
                Elements.TAIL_END_LEFT,
                Elements.TAIL_END_UP,
                Elements.TAIL_END_RIGHT)) {
            return true;
        } else if (fieldWeights.getWeight(point) == FieldWeights.MAX_WEIGHT) {
            return false;
        }

        fieldWeights.setWeight(point, FieldWeights.MAX_WEIGHT);
        boolean result;
        result = isSeeTail(point.getLeftPoint(), fieldWeights);
        if (result) {
            return true;
        }
        result = isSeeTail(point.getRightPoint(), fieldWeights);
        if (result) {
            return true;
        }
        result = isSeeTail(point.getUpPoint(), fieldWeights);
        if (result) {
            return true;
        }
        result = isSeeTail(point.getDownPoint(), fieldWeights);
        if (result) {
            return true;
        }
        return false;
    }
}




