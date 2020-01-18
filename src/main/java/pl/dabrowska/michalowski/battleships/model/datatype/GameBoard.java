package pl.dabrowska.michalowski.battleships.model.datatype;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class GameBoard {
    @NonNull
    private Map<Character, List<Field>> fieldMap;

    public Map<Character, List<Field>> getFieldMap() {
        return Collections.unmodifiableMap(fieldMap);
    }

    public Field.FieldType setFieldPicked(int col, char row) {
        Field field = fieldMap.get(row).get(col);
        field.setPicked(true);
        return field.getFieldType();
    }

    public void setFieldType(int col, char row, Field.FieldType fieldType) {
        fieldMap.get(row).get(col).setFieldType(fieldType);
    }
}
