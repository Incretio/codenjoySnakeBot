package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;

/**
 * Created by incre on 21.01.2017.
 */
public class Run {

    public static void main(String[] args) {
        start(WebSocketRunner.Host.REMOTE,
                new YourSolverVOne(), "my_mail@gmail.com");
    }

    public static void start(WebSocketRunner.Host server, Solver<Board> solver, String botName) {
        try {
            WebSocketRunner.run(server, botName,
                    solver,
                    new Board());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
