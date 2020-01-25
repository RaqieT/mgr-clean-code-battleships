package pl.dabrowska.michalowski.battleships.view.element;

import pl.dabrowska.michalowski.battleships.model.Configuration;
import pl.dabrowska.michalowski.battleships.model.datatype.Field;
import pl.dabrowska.michalowski.battleships.model.datatype.GameBoard;

import java.util.List;
import java.util.Map;

public class GameBoardViewElement implements ViewElement<GameBoard> {
    @Override
    public String prepareText(GameBoard object) {
        Map<Character, List<Field>> fieldMap = object.getFieldMap();
        final StringBuilder builder = new StringBuilder();
        builder.append("#").append("\t");

        for (int i = 0; i < Configuration.getInstance().getColumns(); i++) {
            builder.append(i + 1).append("\t");
        }
        builder.append("\n");

        ViewElement<Field> fieldViewElement = new FieldViewElement();

        Configuration.getInstance().getRowsLetters().forEach(letter -> {
            builder.append(letter).append("\t");
            fieldMap.get(letter).forEach(field -> builder.append(fieldViewElement.prepareText(field)).append("\t"));
            builder.append("\n");
        });
        return builder.toString();
    }
}
