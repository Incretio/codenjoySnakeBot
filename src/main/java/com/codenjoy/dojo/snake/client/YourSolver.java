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
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.model.Elements;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {
    private int maxLength = 0;

    private static final String USER_NAME = "incretio@gmail.com";

    @Override
    public String get(Board board) {
        System.out.println(board.toString());
        maxLength = (board.getSnake().size() > maxLength) ? board.getSnake().size() : maxLength;
        System.out.println("Snake length: " + board.getSnake().size());
        System.out.println("Max snake length: " + maxLength);

        try {
            board.calcSumField();
            return board.getNextStep().toString();
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }


        // TODO: добавить метод проверки, попадёт ли змея в ловушку если сделает так следующий шаг
        // если ячейка является тупиковой, то ей устанавливаем максимальный вес.
        // TODO: если попадаются одинаковые значения, то выбираем ближайшее
    }

    public static void main(String[] args) {
        start(USER_NAME, WebSocketRunner.Host.REMOTE);
    }

    public static void start(String name, WebSocketRunner.Host server) {
        try {
            WebSocketRunner.run(server, name,
                    new YourSolver(),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
