package com.codenjoy.dojo.snake.client.incretiosnakesolver;

class SnakePath implements Comparable<SnakePath> {
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
}