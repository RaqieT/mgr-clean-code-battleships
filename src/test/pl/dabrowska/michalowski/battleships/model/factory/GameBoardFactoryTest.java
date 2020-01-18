package pl.dabrowska.michalowski.battleships.model.factory;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.dabrowska.michalowski.battleships.model.datatype.Field;
import pl.dabrowska.michalowski.battleships.model.datatype.GameBoard;

class GameBoardFactoryTest {

    @Test
    void createEmpty() {
        // given
        GameBoard gameBoard = null;

        // when
        gameBoard = GameBoardFactory.createEmpty();

        // then
        Assert.assertNotNull(gameBoard);
        Assert.assertTrue(gameBoard.getFieldMap().entrySet().stream()
                .allMatch(entry -> entry.getValue().stream()
                        .allMatch(field -> field.getFieldType().equals(Field.FieldType.EMPTY) && !field.isPicked())));
    }
}