package com.codenjoy.dojo.snake.client;


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.snake.client.incretiosnakesolver.IncretioSnakeSolver;

public class YourSolverVOne implements Solver<Board> {
    @Override
    public String get(Board board) {
        return new IncretioSnakeSolver(board).getNextStep().toString();
    }

}
