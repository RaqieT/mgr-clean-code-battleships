package pl.dabrowska.michalowski.battleships.model.datatype;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Field {
    private boolean picked = false;
    @NonNull
    private FieldType fieldType;
}
