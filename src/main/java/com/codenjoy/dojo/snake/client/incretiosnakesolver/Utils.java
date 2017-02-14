package com.codenjoy.dojo.snake.client.incretiosnakesolver;

public class Utils {
    public static int[][] copyArray(int[][] array) {
        if (array == null || array.length == 0 || array[0] == null) {
            return new int[][]{};
        }

        int[][] copy = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i].clone();
        }
        return copy;
    }

    public static void print(int[][] array){
        for (int x = 0; x < array[0].length; x++) {
            for (int y = 0; y < array.length; y++) {
                System.out.print(array[y][x] + "\t");
            }
            System.out.println();
        }
    }

}
