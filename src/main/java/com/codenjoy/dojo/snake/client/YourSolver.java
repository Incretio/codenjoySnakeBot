package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.Solver;

import ru.incretio.codenjoy.qualitybotanalysis.QualityBotsAnalysis;
import ru.incretio.codenjoy.snake.bot.QualitySnakeBotAnalysis;
import ru.incretio.codenjoy.snake.bot.SnakeBot;

public class YourSolver implements Solver<Board> {
    private static final String USER_NAME = "incretio@gmail.com";

    private final QualitySnakeBotAnalysisWrapper qualitySnakeBotAnalysisWrapper; // это наш бот
    {
        SnakeBot bot = new QualitySnakeBotAnalysis(USER_NAME);
        QualityBotsAnalysis.getInstance().addBot(bot);
        qualitySnakeBotAnalysisWrapper = new QualitySnakeBotAnalysisWrapper(bot);
    }

    @Override
    public String get(Board board) {
        try {
            //qualitySnakeBotAnalysisWrapper.doStep(board);
            //System.out.println(qualitySnakeBotAnalysisWrapper);

            board.calcSumField();
            // TODO делать дополнительный просчёт положения смейки, как будто она сама сдвинулась
            return board.getNextStep(board.REMAINING_STEPS
            ).toString();
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }


        // TODO: добавить метод проверки, попадёт ли змея в ловушку если сделает так следующий шаг
        // если ячейка является тупиковой, то ей устанавливаем максимальный вес.
        // TODO: если попадаются одинаковые значения, то выбираем ближайшее
    }

}
