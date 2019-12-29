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

    public Field.FieldType setFieldPicked(int row, char col) {
        Field field = fieldMap.get(col).get(row);
        field.setPicked(true);
        return field.getFieldType();
    }

    public void setFieldType(int row, char col, Field.FieldType fieldType) {
        fieldMap.get(col).get(row).setFieldType(fieldType);
    }
}
