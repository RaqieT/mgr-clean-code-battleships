package pl.dabrowska.michalowski.battleships.model.datatype;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.dabrowska.michalowski.battleships.model.factory.GameBoardFactory;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        // given
        GameBoard gameBoard = GameBoardFactory.createEmpty();
        game = new Game(gameBoard);
    }

    @Test
    void addShot() {
        // given
        int currentShoots = 0;

        // when
        game.addShot();
        game.addShot();

        // then
        currentShoots = game.getShots();
        Assert.assertEquals(2, currentShoots);
    }

    @Test
    void addScore() {
        // given
        int currentScores = 0;

        // when
        game.addScore();
        game.addScore();

        // then
        currentScores = game.getScore();
        Assert.assertEquals(2, currentScores);
    }
}