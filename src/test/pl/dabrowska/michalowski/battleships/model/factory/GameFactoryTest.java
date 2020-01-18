package pl.dabrowska.michalowski.battleships.model.factory;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.dabrowska.michalowski.battleships.model.datatype.Game;

class GameFactoryTest {


    @Test
    void create() {
        // given
        Game game = null;

        // when
        game = GameFactory.create();

        // then
        Assert.assertNotNull(game);
        Assert.assertNotNull(game.getBoard());
        Assert.assertEquals(Game.GameState.PREPARATION, game.getState());
    }
}