package pl.dabrowska.michalowski.battleships.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Configuration {
    private static Configuration instance = new Configuration();

    private static final String COLUMNS_PROPERTY = "battleships.columns";
    private Integer columns;

    private static final String ROWS_PROPERTY = "battleships.rows";
    private Integer rows;

    private Configuration() {
        this.columns = parseIntFromProperty(COLUMNS_PROPERTY, 2);
        this.rows = parseIntFromProperty(ROWS_PROPERTY, 2);
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

    private Integer parseIntFromProperty(String prop, int minValue) {
        int value;
        try {
            value = Integer.parseInt(System.getProperty(prop));
        } catch (NumberFormatException e) {
            return minValue;
        }

        return Math.max(value, minValue);
    }
}
