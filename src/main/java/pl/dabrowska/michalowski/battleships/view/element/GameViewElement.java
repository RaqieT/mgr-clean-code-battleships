package pl.dabrowska.michalowski.battleships.view.element;

import pl.dabrowska.michalowski.battleships.model.datatype.Game;
import pl.dabrowska.michalowski.battleships.model.datatype.GameBoard;

public class GameViewElement implements ViewElement<Game> {
    @Override
    public String prepareText(Game object) {
        final StringBuilder builder = new StringBuilder();

        ViewElement<GameBoard> fieldViewElement = new GameBoardViewElement();
        builder.append("Game state: ").append(object.getState()).append("\n");
        builder.append("Shots: ").append(object.getShots()).append("\n");
        builder.append("Score: ").append(object.getScore()).append("\n");
        builder.append(fieldViewElement.prepareText(object.getBoard()));

        return builder.toString();
    }
}
