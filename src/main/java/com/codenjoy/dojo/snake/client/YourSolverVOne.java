package com.codenjoy.dojo.snake.client;


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.snake.client.incretiosnakesolver.IncretioSnakeSolver;
import ru.incretio.codenjoy.qualitybotanalysis.QualityBotsAnalysis;
import ru.incretio.codenjoy.snake.bot.QualitySnakeBotAnalysis;
import ru.incretio.codenjoy.snake.bot.SnakeBot;

public class YourSolverVOne implements Solver<Board> {
    private static final String USER_NAME = "incretio@gmail.com";

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

        return new IncretioSnakeSolver(board).getNextStep().toString();
    }

}
