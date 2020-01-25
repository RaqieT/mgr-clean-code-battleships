package pl.dabrowska.michalowski.battleships.model.factory;

import pl.dabrowska.michalowski.battleships.model.datatype.Game;

public class GameFactory {
    private GameFactory() {}
    public static Game create() {
        return new Game(GameBoardFactory.createEmpty());
    }
}
