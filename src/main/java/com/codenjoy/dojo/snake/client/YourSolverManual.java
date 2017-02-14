package com.codenjoy.dojo.snake.client;


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import ru.incretio.codenjoy.qualitybotanalysis.QualityBotsAnalysis;
import ru.incretio.codenjoy.snake.bot.QualitySnakeBotAnalysis;
import ru.incretio.codenjoy.snake.bot.SnakeBot;

import java.util.Scanner;

public class YourSolverManual implements Solver<Board> {
    private static final String USER_NAME = "incretio_manual@gmail.com";


    private final QualitySnakeBotAnalysisWrapper qualitySnakeBotAnalysisWrapper; // это наш бот

    {
        SnakeBot bot = new QualitySnakeBotAnalysis(USER_NAME);
        QualityBotsAnalysis.getInstance().addBot(bot);
        qualitySnakeBotAnalysisWrapper = new QualitySnakeBotAnalysisWrapper(bot);
    }

    @Override
    public String get(Board board) {
        qualitySnakeBotAnalysisWrapper.doStep(board);
        System.out.println(qualitySnakeBotAnalysisWrapper);

        String inLine = new Scanner(System.in).nextLine();
        System.out.println(inLine);
        switch (inLine) {
            case "4":
                return Direction.LEFT.toString();
            case "8":
                return Direction.UP.toString();
            case "6":
                return Direction.RIGHT.toString();
            case "2":
                return Direction.DOWN.toString();
            default:
                return Direction.DOWN.toString();
        }

    }

    @Override
    public String toString() {
        return USER_NAME;
    }
}
