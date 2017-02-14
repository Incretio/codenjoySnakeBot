package com.codenjoy.dojo.snake.client;

/**
 * Created by incre on 22.01.2017.
 */

import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;

/**
 * User: sanja
 * Date: 03.08.16
 * Time: 20:57
 */
public class Test {

    private static Solver ai;

    private static Board board(String board) {
        return (Board) new Board().forString(board);
    }

    public static void test_new(){
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼☺            ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.LEFT);
        if (true) return;
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                "☼╔═══════════╗☼\n" +
                "☼╚╗╔╗╔═══════╝☼\n" +
                "☼╔╝║║║╔══════╗☼\n" +
                "☼╚╗║╚╝║╔══╗╔═╝☼\n" +
                "☼☺▼║╔═╝║╔═╝╚═╗☼\n" +
                "☼  ║╚╗╔╝╚══╗╔╝☼\n" +
                "☼  ╚╗╚╝╔═══╝╚╗☼\n" +
                "☼   ║╔═╝╔══╗╔╝☼\n" +
                "☼   ║╚══╝╔═╝╚╗☼\n" +
                "☼   ║╔╗╔╗║╔═╗║☼\n" +
                "☼   ║║╚╝╚╝║╔╝║☼\n" +
                "☼   ╚╝ ╔╗ ║╚╗║☼\n" +
                "☼   ╘══╝╚═╝ ╚╝☼\n" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.LEFT);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                "☼╔═══════════╗☼\n" +
                "☼╚╗╔╗╔═══════╝☼\n" +
                "☼╔╝║║║╔══════╗☼\n" +
                "☼╚►║╚╝║╔══╗╔═╝☼\n" +
                "☼☺☻║╔═╝║╔═╝╚═╗☼\n" +
                "☼  ║╚╗╔╝╚══╗╔╝☼\n" +
                "☼  ╚╗╚╝╔═══╝╚╗☼\n" +
                "☼ ╘╗║╔═╝╔══╗╔╝☼\n" +
                "☼╔═╝║╚══╝╔═╝╚╗☼\n" +
                "☼╚═╗║╔╗╔╗║╔═╗║☼\n" +
                "☼  ║║║╚╝╚╝║╔╝║☼\n" +
                "☼  ║╚╝ ╔╗ ║╚╗║☼\n" +
                "☼  ╚═══╝╚═╝ ╚╝☼\n" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.DOWN);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                "☼ ☻           ☼\n" +
                "☼ ▲ ☺ ╔═╗     ☼\n" +
                "☼ ║  ╔╝ ╚╗    ☼\n" +
                "☼ ╚╗ ╚══╗╚╗   ☼\n" +
                "☼  ║  ╘╗╚╗╚╗  ☼\n" +
                "☼  ╚══╗╚╗╚╗║  ☼\n" +
                "☼     ╚╗╚═╝║  ☼\n" +
                "☼      ╚╗ ╔╝  ☼\n" +
                "☼       ║╔╝   ☼\n" +
                "☼       ╚╝    ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.UP);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼╔═════╗      ☼" +
                "☼║     ╚╗     ☼" +
                "☼╚═╗    ╚╗    ☼" +
                "☼╔╗║ ☺  ╔╝    ☼" +
                "☼║║║ ╔╗ ║╔═══╗☼" +
                "☼║╚╝ ║║ ╚╝   ║☼" +
                "☼╚═══╝║╔═════╝☼" +
                "☼ ╘═══╝╚═╗    ☼" +
                "☼  ╔═════╝  ☻ ☼" +
                "☼  ╚════╗     ☼" +
                "☼ ╔═════╝     ☼" +
                "☼◄╝           ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.UP);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼    ╔╗       ☼" +
                "☼   ╔╝╚════╗☺ ☼" +
                "☼   ║╔════╗╚═╗☼" +
                "☼   ╚╝╔══╗║ ╔╝☼" +
                "☼   ╔═╝╔╗╚╝ ╚╗☼" +
                "☼   ║╔═╝╚╗ ▲ ║☼" +
                "☼   ║║╔══╝ ║ ║☼" +
                "☼   ║║║☻╔╗ ║ ║☼" +
                "☼   ║║╚╗║║ ║╔╝☼" +
                "☼   ║╚╗║║║╓╚╝ ☼" +
                "☼   ║╔╝║║╚╝╔═╗☼" +
                "☼   ║║╔╝╚══╝╔╝☼" +
                "☼   ╚╝╚═════╝ ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.LEFT);
    }


    public static void test_forward_direction() {
        asertAI("☼☼☼☼☼" +
                "☼   ☼" +
                "☼ ☺ ☼" +
                "☼ ▲ ☼" +
                "☼☼☼☼☼", Direction.UP);

        asertAI("☼☼☼☼☼" +
                "☼ ▼ ☼" +
                "☼ ☺ ☼" +
                "☼   ☼" +
                "☼☼☼☼☼", Direction.DOWN);

        asertAI("☼☼☼☼☼" +
                "☼   ☼" +
                "☼ ☺◄☼" +
                "☼   ☼" +
                "☼☼☼☼☼", Direction.LEFT);

        asertAI("☼☼☼☼☼" +
                "☼   ☼" +
                "☼►☺ ☼" +
                "☼   ☼" +
                "☼☼☼☼☼", Direction.RIGHT);

    }

    public static void test_invert_direction() {
        asertAI("☼☼☼☼☼☼" +
                "☼  ☺ ☼" +
                "☼  ╕ ☼" +
                "☼  ▼ ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", Direction.LEFT);

        asertAI("☼☼☼☼☼☼" +
                "☼ ▲  ☼" +
                "☼ ╝  ☼" +
                "☼ ☺  ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", Direction.LEFT);

        asertAI("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼◄╕ ☺☼" +
                "☼    ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", Direction.UP);

        asertAI("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼☺═► ☼" +
                "☼    ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", Direction.UP);

    }

    public static void test_difficult_choice_() {
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼        ╔╗   ☼" +
                "☼        ▼╚══╗☼" +
                "☼      ☻    ╔╝☼" +
                "☼     ╘═════╝ ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼          ☺  ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.LEFT);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼        ╔╗   ☼" +
                "☼        ║╚══╗☼" +
                "☼      ☻ ▼  ╔╝☼" +
                "☼     ╘═════╝ ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼          ☺  ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.LEFT);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼    ╔═════╗  ☼" +
                "☼ ☻  ╚═╗   ╚╗ ☼" +
                "☼     ╘╝    ╚╗☼" +
                "☼            ║☼" +
                "☼            ║☼" +
                "☼            ║☼" +
                "☼            ║☼" +
                "☼            ║☼" +
                "☼           ╔╝☼" +
                "☼ ╔══╗ ╔════╝ ☼" +
                "☼ ╚╗╔╝╔╝      ☼" +
                "☼◄═╝╚═╝       ☼" +
                "☼    ☺        ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.UP);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼   ▲    ☺    ☼" +
                "☼   ║╔╗       ☼" +
                "☼   ║║╚╗      ☼" +
                "☼  ╔╝║ ╚════╗ ☼" +
                "☼  ║╔╝      ╚╗☼" +
                "☼ ╔╝║        ║☼" +
                "☼ ║╔╝ ╓      ║☼" +
                "☼ ║╚╗ ║      ║☼" +
                "☼ ╚═╝ ╚══╗   ║☼" +
                "☼        ║   ║☼" +
                "☼   ☻    ╚═══╝☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.LEFT);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼   ╔╗        ☼" +
                "☼   ║╚╗       ☼" +
                "☼   ║ ╚╗      ☼" +
                "☼   ║  ╚╗     ☼" +
                "☼   ║   ╚╗    ☼" +
                "☼   ║    ╚╗   ☼" +
                "☼   ║     ╚╗  ☼" +
                "☼   ║    ☻ ╚╗ ☼" +
                "☼  ╔╝       ╚╕☼" +
                "☼ ╔╝          ☼" +
                "☼ ▼           ☼" +
                "☼☺            ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.LEFT);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼ ╔╗          ☼" +
                "☼ ║╚╗         ☼" +
                "☼ ╚╕║╔══╗╔═╗  ☼" +
                "☼   ║╚═╗╚╝ ║  ☼" +
                "☼   ╚══╝ ☻╔╝  ☼" +
                "☼    ╔════╝   ☼" +
                "☼   ╔╝        ☼" +
                "☼  ◄╝         ☼" +
                "☼  ☺          ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.DOWN);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼      ☺      ☼" +
                "☼      ◄═╗    ☼" +
                "☼       ╔╝    ☼" +
                "☼     ╔═╝     ☼" +
                "☼     ╚╗      ☼" +
                "☼      ╚╗     ☼" +
                "☼       ╚═══╗ ☼" +
                "☼           ║ ☼" +
                "☼        ☻  ║ ☼" +
                "☼╔═╗       ╔╝ ☼" +
                "☼║╘╝  ╔════╝  ☼" +
                "☼║   ╔╝       ☼" +
                "☼╚═══╝        ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.UP);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼ ╔═══════╗╔╗ ☼" +
                "☼ ╚══════╗║║║ ☼" +
                "☼        ║║║║ ☼" +
                "☼        ║║║║ ☼" +
                "☼        ║║║║ ☼" +
                "☼       ╔╝║║║ ☼" +
                "☼      ╔╝☻╚╝║ ☼" +
                "☼     ╔╝    ║ ☼" +
                "☼     ╙     ║ ☼" +
                "☼          ◄╝ ☼" +
                "☼          ☺  ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.DOWN);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼          ☻  ☼" +
                "☼             ☼" +
                "☼   ╔═╗       ☼" +
                "☼  ╔╝╔╝       ☼" +
                "☼ ╔╝╔╝        ☼" +
                "☼ ║◄╝         ☼" +
                "☼☺║           ☼" +
                "☼ ║           ☼" +
                "☼ ║           ☼" +
                "☼ ╙           ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.DOWN);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼   ☺         ☼" +
                "☼   ◄═╗   ☻   ☼" +
                "☼     ╚═══╗   ☼" +
                "☼         ╚═╗ ☼" +
                "☼           ║ ☼" +
                "☼          ╔╝ ☼" +
                "☼          ║  ☼" +
                "☼          ╚╗ ☼" +
                "☼           ║ ☼" +
                "☼           ╙ ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.UP);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼      ╔╗     ☼" +
                "☼    ☻ ║║     ☼" +
                "☼      ║╚╗    ☼" +
                "☼      ║ ╚╗   ☼" +
                "☼      ║  ╚══╗☼" +
                "☼      ║     ║☼" +
                "☼      ║     ║☼" +
                "☼      ║  ☺  ║☼" +
                "☼      ║     ║☼" +
                "☼      ║     ║☼" +
                "☼      ║     ╙☼" +
                "☼      ╚═╗    ☼" +
                "☼        ╚►   ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.UP);
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼       ☻     ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼       ╓     ☼" +
                "☼       ╚══►  ☼" +
                "☼          ☺  ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.DOWN);
        /*asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼         ╔►  ☼" +
                "☼    ╔════╝   ☼" +
                "☼    ╚╗       ☼" +
                "☼     ╚╗      ☼" +
                "☼      ╚╗     ☼" +
                "☼       ╚╗    ☼" +
                "☼        ╚╗   ☼" +
                "☼         ╚╗ ☺☼" +
                "☼     ☻    ╚╗ ☼" +
                "☼           ╚╗☼" +
                "☼           ╔╝☼" +
                "☼          ╔╝ ☼" +
                "☼     ╘════╝  ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.);*/

    }

    private static void asertAI(String textBoard, Direction expected) {
        String actual = new YourSolverVOne().get(board(textBoard));
        System.out.println(
                "expected = " + expected.toString() + ", actual = " + actual +
                        ", result = " + (expected.toString() == actual));
        System.out.println();
    }

    public static void main(String[] args) {
        test_new();
    }

}
