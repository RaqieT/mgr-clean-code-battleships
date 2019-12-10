package pl.dabrowska.michalowski.battleships.controller.dataparser;

import pl.dabrowska.michalowski.battleships.exception.WrongInputException;
import pl.dabrowska.michalowski.battleships.model.Configuration;
import pl.dabrowska.michalowski.battleships.model.service.cmd.ShootFieldCommand;

import java.util.stream.Collectors;

public class ShootFieldCommandParser {
    public static ShootFieldCommand parse(String input) throws WrongInputException {
        String upperCasedInput = input.toUpperCase();
        int inputLength = upperCasedInput.length();
        if (inputLength < 2 || inputLength > 3) {
            throw new WrongInputException("Input length is wrong: " + inputLength + " expected [2-3]");
        }

        char c = upperCasedInput.charAt(0);

        if (!Configuration.getInstance().getRowsLetters().contains(c)) {
            throw new WrongInputException("Unrecognized column letter: " + c);
        }

        String textAfterFirstChar = upperCasedInput.chars()
                .mapToObj(x -> (char) x).skip(1).map(String::valueOf)
                .collect(Collectors.joining());

        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(textAfterFirstChar);
        } catch (NumberFormatException e) {
            throw new WrongInputException("Cannot parse " + textAfterFirstChar + " as integer");
        }

        if (Configuration.getInstance().getColumns() < parsedNumber) {
            throw new WrongInputException("Wrong row number: " + parsedNumber + " expected [1-" + Configuration.getInstance().getColumns() + "]");
        }

        return new ShootFieldCommand(parsedNumber, c);
    }
}
