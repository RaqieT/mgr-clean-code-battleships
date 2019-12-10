package pl.dabrowska.michalowski.battleships.view.renderer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.dabrowska.michalowski.battleships.exception.RenderingException;
import pl.dabrowska.michalowski.battleships.model.datatype.GameBoard;
import pl.dabrowska.michalowski.battleships.view.element.GameBoardViewElement;

@RequiredArgsConstructor
public class ConsoleRenderer {
    @NonNull
    private GameBoardViewElement gameBoardConsoleRenderer;

    public void updateView(GameBoard gameBoard) throws RenderingException {
        updateView(gameBoard, null);
    }

    public void updateView(GameBoard gameBoard, String alert) {
        clearConsole();
        StringBuilder builder = new StringBuilder();
        builder.append("BATTLESHIPS").append("\n");
        builder.append(gameBoardConsoleRenderer.prepareText(gameBoard));
        if (alert != null) {
            builder.append("Problem: ").append(alert).append("\n");
        }
        builder.append("Enter number of field to shoot eg. A2: ");
        System.out.println(builder);
    }

    private final static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (final Exception e) {
            // If there is exception with console clearing, just don't clear it
        }
    }
}
