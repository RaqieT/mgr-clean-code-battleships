package pl.dabrowska.michalowski.battleships.model.factory;

import pl.dabrowska.michalowski.battleships.model.Configuration;
import pl.dabrowska.michalowski.battleships.model.datatype.Field;
import pl.dabrowska.michalowski.battleships.model.datatype.GameBoard;

import java.util.*;

public class GameBoardFactory {

    public static GameBoard createEmpty() {
        Map<Character, List<Field>> map = new LinkedHashMap<>();

        Configuration.getInstance().getRowsLetters().forEach(letter -> {
            List<Field> fields = new ArrayList<>();
            for (int i = 0; i < Configuration.getInstance().getColumns(); i++) {
                fields.add(new Field(Field.FieldType.EMPTY));
            }
            map.put(letter, fields);
        });

        return new GameBoard(map);
    }
}
