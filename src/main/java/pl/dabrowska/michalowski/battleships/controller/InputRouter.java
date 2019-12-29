package pl.dabrowska.michalowski.battleships.controller;

import org.javatuples.Pair;
import pl.dabrowska.michalowski.battleships.exception.WrongInputException;
import pl.dabrowska.michalowski.battleships.util.CommandExecutor;

import java.util.*;
import java.util.stream.Collectors;

public class InputRouter {
    private Map<String, Pair<CommandExecutor, String>> commandMap = new LinkedHashMap<>();

    public Set<String> getCommands() {
        return commandMap.keySet();
    }

    public <T> void addCommand(String command, CommandExecutor executor, String help) {
        commandMap.put(command, Pair.with(executor, help));
    }

    public String getAllCommandsHelp() {
        return commandMap.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue().getValue1())
                .collect(Collectors.joining("\n"));
    }

    @SuppressWarnings("unchecked")
    public Optional<String> interpretInput(String input) throws WrongInputException {
        Optional<Map.Entry<String, Pair<CommandExecutor, String>>> optionalCmdEntry = commandMap.entrySet().stream()
                .filter(entry -> input.toLowerCase().startsWith(entry.getKey().toLowerCase())).findAny();

        if (!optionalCmdEntry.isPresent()) {
            throw new WrongInputException("Unknown command: " + input.split(" ")[0]);
        }

        Map.Entry<String, Pair<CommandExecutor, String>> cmdEntry = optionalCmdEntry.get();

        String cmdArgs = input.toLowerCase().replaceFirst(cmdEntry.getKey(), "");
        if (cmdArgs.startsWith(" ")) {
            cmdArgs = cmdArgs.replaceFirst(" ", "");
        }

        return cmdEntry.getValue().getValue0().execute(cmdArgs);
    }
}
