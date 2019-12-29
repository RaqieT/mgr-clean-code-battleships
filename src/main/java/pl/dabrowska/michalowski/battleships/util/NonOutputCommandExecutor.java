package pl.dabrowska.michalowski.battleships.util;

import pl.dabrowska.michalowski.battleships.exception.WrongInputException;

import java.util.Optional;

@FunctionalInterface
public interface NonOutputCommandExecutor extends CommandExecutor {
    default Optional<String> execute(String s) throws WrongInputException {
        executeWithoutOutput(s);
        return Optional.empty();
    }

    void executeWithoutOutput(String s) throws WrongInputException;
}
