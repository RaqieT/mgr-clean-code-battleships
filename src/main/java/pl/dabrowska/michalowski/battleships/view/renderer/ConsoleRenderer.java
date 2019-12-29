package pl.dabrowska.michalowski.battleships.view.renderer;

import lombok.Data;
import lombok.NonNull;
import pl.dabrowska.michalowski.battleships.exception.RenderingException;
import pl.dabrowska.michalowski.battleships.model.datatype.Game;
import pl.dabrowska.michalowski.battleships.view.element.GameViewElement;

import java.util.HashSet;
import java.util.Set;

@Data
public class ConsoleRenderer {
    private GameViewElement gameViewElement = new GameViewElement();

    public void updateView(Game game) throws RenderingException {
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
            System.out.println(builder.toString());
            return;
        }
        builder.append("Enter the command (type help for command list): ");
        System.out.println(builder);
    }

    private final static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (final Exception e) {
            // If there is problem with console clearing, just don't clear it
        }
    }
}
