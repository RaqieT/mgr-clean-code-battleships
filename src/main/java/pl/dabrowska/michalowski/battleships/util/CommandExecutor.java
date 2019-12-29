package pl.dabrowska.michalowski.battleships.util;

import pl.dabrowska.michalowski.battleships.exception.WrongInputException;

import java.util.Optional;

@FunctionalInterface
public interface CommandExecutor {
    Optional<String> execute(String s) throws WrongInputException;
}
