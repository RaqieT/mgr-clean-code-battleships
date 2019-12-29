package pl.dabrowska.michalowski.battleships.controller;

import lombok.NonNull;
import pl.dabrowska.michalowski.battleships.controller.parser.LocationParser;
import pl.dabrowska.michalowski.battleships.model.service.GameService;
import pl.dabrowska.michalowski.battleships.exception.WrongInputException;
import pl.dabrowska.michalowski.battleships.util.NonOutputCommandExecutor;
import pl.dabrowska.michalowski.battleships.view.renderer.ConsoleRenderer;

import java.util.Optional;

public class ConsoleViewController {
    @NonNull
    private GameService model;
    @NonNull
    private ConsoleRenderer view;
    @NonNull
    private InputRouter router = new InputRouter();

    public ConsoleViewController(@NonNull GameService model, @NonNull ConsoleRenderer view) {
        this.model = model;
        this.view = view;

        registerCommands();
        view.updateView(model.getGame());
    }

    private void registerCommands() {
        router.addCommand("ship", (NonOutputCommandExecutor) cmdArgs -> model.addShip(LocationParser.getLocation(cmdArgs)),
                "Phase: PREPARATION, places a ship in location. Example: ship A1");
        router.addCommand("unship", (NonOutputCommandExecutor) cmdArgs -> model.removeShip(LocationParser.getLocation(cmdArgs)),
                "Phase: PREPARATION, remove ship from location. Example: unship A1");
        router.addCommand("random", (NonOutputCommandExecutor) cmdArgs -> model.addShipsOnRandomLocations(),
                "Phase: PREPARATION, places ships on random location. Example: random");
        router.addCommand("clear", (NonOutputCommandExecutor) cmdArgs -> model.clearGameBoard(),
                "Phase: PREPARATION, remove all ships from game board. Example: clear");
        router.addCommand("start", (NonOutputCommandExecutor) cmdArgs -> model.startGame(),
                "Phase: PREPARATION, starts the game (changes phase to IN_PROGRESS). Example: start");
        router.addCommand("shoot", cmdArgs -> Optional.of(model.shootField(LocationParser.getLocation(cmdArgs))),
                "Phase: IN_PROGRESS, shoots a ship. Example: shoot A1");
        router.addCommand("exit", (NonOutputCommandExecutor) cmdArgs -> System.exit(0),
                "Phase: ANY, exit game. Example: exit");
        router.addCommand("help", cmdArgs -> Optional.of(router.getAllCommandsHelp()),
                "Phase: ANY, prints help. Example: help");
    }

    public void readInput(String input) {
        Optional<String> output;
        try {
            output = router.interpretInput(input);
        } catch (WrongInputException e) {
            view.updateView(model.getGame(), e.getMessage());
            return;
        }

        if (output.isPresent()) {
            view.updateView(model.getGame(), output.get());
        } else {
            view.updateView(model.getGame());
        }
    }
}
