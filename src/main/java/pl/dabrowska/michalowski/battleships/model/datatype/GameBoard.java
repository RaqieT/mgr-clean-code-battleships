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

    public void setFieldPicked(int row, char col) {
        fieldMap.get(col).get(row).setPicked(true);
    }
}
