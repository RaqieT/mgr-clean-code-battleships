package pl.dabrowska.michalowski.battleships.model.factory;

import pl.dabrowska.michalowski.battleships.model.Configuration;
import pl.dabrowska.michalowski.battleships.model.datatype.Field;
import pl.dabrowska.michalowski.battleships.model.datatype.FieldType;
import pl.dabrowska.michalowski.battleships.model.datatype.GameBoard;

import java.util.*;

public class GameBoardFactory {

    public static GameBoard createEmpty() {
        Map<Character, List<Field>> map = new LinkedHashMap<>();

        Configuration.getInstance().getRowsLetters().forEach(letter -> {
            List<Field> fields = new ArrayList<>();
            for (int i = 0; i < Configuration.getInstance().getColumns(); i++) {
                fields.add(new Field(FieldType.EMPTY));
            }
            map.put(letter, fields);
        });

        return new GameBoard(map);
    }

    //    No.	Class of ship	Size
    //    1	    Carrier         5
    //    2	    Battleship      4
    //    3	    Cruiser         3
    //    4	    Submarine       3
    //    5	    Destroyer       2
    // Source: wikipedia.com
    public static GameBoard random() {
        // TODO: implement
        return null;
    }
}
