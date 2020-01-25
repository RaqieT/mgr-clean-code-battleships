package pl.dabrowska.michalowski.battleships;

import pl.dabrowska.michalowski.battleships.controller.ConsoleViewController;
import pl.dabrowska.michalowski.battleships.model.service.GameService;
import pl.dabrowska.michalowski.battleships.view.renderer.ConsoleRenderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;

            GameService model = new GameService();
            ConsoleRenderer view = new ConsoleRenderer();
            ConsoleViewController consoleViewController = new ConsoleViewController(model, view);

            while ((line = bufferedReader.readLine()) != null) {
                consoleViewController.readInput(line);
            }
        }
    }
}
