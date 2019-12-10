package pl.dabrowska.michalowski.battleships.controller;

import lombok.NonNull;
import pl.dabrowska.michalowski.battleships.controller.dataparser.ShootFieldCommandParser;
import pl.dabrowska.michalowski.battleships.model.service.GameService;
import pl.dabrowska.michalowski.battleships.exception.WrongInputException;
import pl.dabrowska.michalowski.battleships.view.renderer.ConsoleRenderer;

public class ConsoleViewController {
    @NonNull
    private GameService model;
    @NonNull
    private ConsoleRenderer view;

    public ConsoleViewController(@NonNull GameService model, @NonNull ConsoleRenderer view) {
        this.model = model;
        this.view = view;

        view.updateView(model.getGameBoard());
    }

    public void readInput(String input) {
        try {
            model.shootField(ShootFieldCommandParser.parse(input));
        } catch (WrongInputException e) {
            view.updateView(model.getGameBoard(), e.getMessage());
            return;
        }
        view.updateView(model.getGameBoard());
    }
}
