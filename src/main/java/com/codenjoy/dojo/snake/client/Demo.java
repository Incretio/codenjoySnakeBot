package com.codenjoy.dojo.snake.client;

import java.util.*;

/**
 * Created by incre on 22.01.2017.
 */
public class Demo {

    static class SnakePath {
        public int stepSum;
        public int stepToEaten;

        public SnakePath(int stepSum, int stepToEaten) {
            this.stepSum = stepSum;
            this.stepToEaten = stepToEaten;
        }

        @Override
        public String toString() {
            return "stepSum = " + stepSum + ", stepToEaten = " + stepToEaten;
        }
    }
    public static void main(String[] args) {
        List<SnakePath> list = new ArrayList<>();

        list.add(new SnakePath(-99982, 11));
        list.add(new SnakePath(Integer.MAX_VALUE, Integer.MAX_VALUE));

        SnakePath min = Collections.min(list, new Comparator<SnakePath>() {
            @Override
            public int compare(SnakePath o1, SnakePath o2) {
                if (o1.stepToEaten != o2.stepToEaten){
                    return o1.stepToEaten - o2.stepToEaten;
                } else {
                    return o1.stepSum - o2.stepSum;
                }
            }
        });


        System.out.println(min);


    }
}
