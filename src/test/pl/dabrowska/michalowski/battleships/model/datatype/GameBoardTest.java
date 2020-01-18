package pl.dabrowska.michalowski.battleships.model.datatype;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.dabrowska.michalowski.battleships.model.factory.GameBoardFactory;

class GameBoardTest {

    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        // given
        gameBoard = GameBoardFactory.createEmpty();
    }

    @Test
    void getFieldMap() {
        // when
        gameBoard.setFieldType(5, 'A', Field.FieldType.SHIP);

        // then
        Assert.assertEquals(Field.FieldType.SHIP, gameBoard.getFieldMap().get('A').get(5).getFieldType());
    }

    @Test
    void setFieldPicked() {
        // given
        int col = 0;
        char row = 'A';

        // when
        gameBoard.setFieldPicked(col, row);

        // then
        Assert.assertTrue(gameBoard.getFieldMap().get('A').get(0).isPicked());
    }

    @Test
    void setFieldTypeShip() {
        // given
        int col = 0;
        char row = 'A';

        // when
        gameBoard.setFieldType(col, row, Field.FieldType.SHIP);

        // then
        Assert.assertEquals(Field.FieldType.SHIP, gameBoard.getFieldMap().get('A').get(0).getFieldType());
    }

    @Test
    void setFieldTypeEmpty() {
        // given
        int col = 0;
        char row = 'A';

        // when
        gameBoard.setFieldType(col, row, Field.FieldType.EMPTY);

        // then
        Assert.assertEquals(Field.FieldType.EMPTY, gameBoard.getFieldMap().get('A').get(0).getFieldType());
    }
}