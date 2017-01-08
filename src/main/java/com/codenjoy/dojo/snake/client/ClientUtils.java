package com.codenjoy.dojo.snake.client;

/**
 * Created by incre on 08.01.2017.
 */
public class ClientUtils {
    public static void printField(int[][] field){
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                System.out.print(field[x][y] + "\t");
            }
            System.out.println();
        }
    }
}
