package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Point;
import ru.incretio.codenjoy.snake.bot.SnakeBot;

/**
 * Created by incre on 30.01.2017.
 */
public class QualitySnakeBotAnalysisWrapper {
    private final SnakeBot snakeBot;
    private Point lastApplePosition;
    private Point lastStonePosition;
    private Board board;

    public QualitySnakeBotAnalysisWrapper(SnakeBot snakeBot) {
        this.snakeBot = snakeBot;
    }

    public void doStep(Board board) {
        this.board = board;
        snakeBot.doStep(
                getSnakeLength(),
                isDeath(),
                isEatenApple(),
                isEatenStone());
        saveAppleAndStonePosition();
    }

    private int getSnakeLength() {
        return board.getSnake().size();
    }

    private boolean isDeath() {
        if (lastApplePosition == null || lastStonePosition == null) {
            return false;
        }

        boolean appleReplaced = !isPointsEquals(board.getApples().get(0), lastApplePosition);
        boolean stoneReplaced = !isPointsEquals(board.getStones().get(0), lastStonePosition);

        return appleReplaced && stoneReplaced;
    }

    private boolean isEatenApple() {
        if (lastApplePosition == null) {
            return false;
        }
        return !board.isGameOver() && (!isDeath() && isPointsEquals(lastApplePosition, board.getHead()));
    }

    private boolean isEatenStone() {
        if (lastStonePosition == null) {
            return false;
        }

        return !board.isGameOver() && (!isDeath() && isPointsEquals(lastStonePosition, board.getHead()));
    }

    private void saveAppleAndStonePosition() {
        lastApplePosition = board.getApples().get(0);
        lastStonePosition = board.getStones().get(0);
    }

    private boolean isPointsEquals(Point p1, Point p2) {
        return (p1.getX() == p2.getX()) && (p1.getY() == p2.getY());
    }

    @Override
    public String toString() {
        return snakeBot.toString();
    }
}
