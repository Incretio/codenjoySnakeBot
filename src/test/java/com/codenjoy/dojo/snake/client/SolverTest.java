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


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.services.Dice;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: sanja
 * Date: 03.08.16
 * Time: 20:57
 */
public class SolverTest {

    private Solver ai;

    @Before
    public void setup() {
        ai = new YourSolver(100);
    }

    private Board board(String board) {
        return (Board) new Board().forString(board);
    }

    /**
     * Входное поле должно быть квадратнымвыаdsfsdваыва!
     */
    @Test
    public void test_forward_direction() {
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

    @Test
    public void test_new(){
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
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼");
    }

    @Test
    public void test_invert_direction() {
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

    @Test
    public void test_difficult_choice_() {
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
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.DOWN);
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
        asertAI("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                "☼             ☼\n" +
                "☼             ☼\n" +
                "☼          ☻  ☼\n" +
                "☼             ☼\n" +
                "☼   ╔═╗       ☼\n" +
                "☼  ╔╝╔╝       ☼\n" +
                "☼ ╔╝╔╝        ☼\n" +
                "☼ ║◄╝         ☼\n" +
                "☼☺║           ☼\n" +
                "☼ ║           ☼\n" +
                "☼ ║           ☼\n" +
                "☼ ╙           ☼\n" +
                "☼             ☼\n" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", Direction.DOWN);

    }

    private void asertAI(String textBoard, Direction expected) {
        String actual = new YourSolver().get(board(textBoard)));
        System.out.println(actual);


        assertEquals(expected.toString(), actual);
    }

    public static void main(String[] args) {
        test_difficult_choice_();
    }

}
