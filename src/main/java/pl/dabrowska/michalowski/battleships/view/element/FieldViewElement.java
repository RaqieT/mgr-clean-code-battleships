package pl.dabrowska.michalowski.battleships.view.element;

import pl.dabrowska.michalowski.battleships.model.datatype.Field;
import pl.dabrowska.michalowski.battleships.exception.RenderingException;

public class FieldViewElement implements ViewElement<Field> {
    @Override
    public String prepareText(Field field) throws RenderingException {
        switch (field.getFieldType()) {
            case SHIP:
                if (field.isPicked()) {
                    return "X";
                }
                return "S";
            case EMPTY:
                if (field.isPicked()) {
                    return "M";
                }
                return " ";
        }

        throw new RenderingException("Unsupported field type");
    }
}
