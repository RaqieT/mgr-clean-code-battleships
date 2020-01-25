package pl.dabrowska.michalowski.battleships.view.renderer;

import org.apache.log4j.Logger;
import lombok.Data;
import pl.dabrowska.michalowski.battleships.model.datatype.Game;
import pl.dabrowska.michalowski.battleships.view.element.GameViewElement;

@Data
public class ConsoleRenderer {
    static final Logger logger = Logger.getLogger(ConsoleRenderer.class);

    private GameViewElement gameViewElement = new GameViewElement();

    public void updateView(Game game) {
        updateView(game, null);
    }

    public void updateView(Game game, String info) {
        clearConsole();
        StringBuilder builder = new StringBuilder();
        builder.append("BATTLESHIPS").append("\n");
        builder.append(gameViewElement.prepareText(game));
        if (info != null) {
            builder.append("=============================================").append("\n");
            builder.append("INFO: ").append("\n");
            builder.append(info).append("\n");
            builder.append("=============================================").append("\n");
        }
        if (game.getState().equals(Game.GameState.WON)) {
            builder.append("You won, type exit to end:");
            logger.info(builder.toString());
            return;
        }
        builder.append("Enter the command (type help for command list): ");
       logger.info(builder.toString());
    }

    private static final void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (final Exception e) {
            // If there is problem with console clearing, just don't clear it
        }
    }
}
