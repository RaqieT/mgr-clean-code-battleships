package pl.dabrowska.michalowski.battleships.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class Configuration {
    private static Configuration instance = new Configuration();

    private static final String COLUMNS_PROPERTY = "battleships.columns";
    private Integer columns = 10;

    private static final String ROWS_PROPERTY = "battleships.rows";
    private Integer rows = 10;

    private Configuration() {
        parseIntFromProperty(COLUMNS_PROPERTY, 2).ifPresent(value -> this.columns = value);
        parseIntFromProperty(ROWS_PROPERTY, 2).ifPresent(value -> this.rows = value);
    }

    public static Configuration getInstance() {
        return instance;
    }

    public List<Character> getRowsLetters() {
        List<Character> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            char l = (char)((int)'A' + i);
            result.add(l);
        }
        return result;
    }

    private Optional<Integer> parseIntFromProperty(String prop, int minValue) {
        int value;
        String property = System.getProperty(prop);
        if (property == null) {
            return Optional.empty();
        }
        try {
            value = Integer.parseInt(property);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

        return Optional.of(Math.max(value, minValue));
    }
}
