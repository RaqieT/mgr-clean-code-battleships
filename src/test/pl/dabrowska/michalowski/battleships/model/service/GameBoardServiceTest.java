package pl.dabrowska.michalowski.battleships.model.service;

import org.javatuples.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.dabrowska.michalowski.battleships.model.datatype.Field;
import pl.dabrowska.michalowski.battleships.model.datatype.GameBoard;
import pl.dabrowska.michalowski.battleships.model.factory.GameBoardFactory;

class GameBoardServiceTest {

    private GameBoardService gameBoardService;
    private Pair<Integer, Character> firstLocation;
    private Pair<Integer, Character> secondLocation;

    @BeforeEach
    void setUp() {
        // given
        GameBoard gameBoard = GameBoardFactory.createEmpty();
        gameBoardService = new GameBoardService(gameBoard);

        firstLocation = new Pair<>(5, 'A');
        secondLocation = new Pair<>(5, 'B');
    }

    @Test
    void pickFieldInLocationAndCheckIfItIsShip() {
        // when
        gameBoardService.setShipInLocation(firstLocation);

        // then
        Assert.assertEquals(Field.FieldType.SHIP, gameBoardService.getGameBoard().getFieldMap()
                .get(firstLocation.getValue1()).get(firstLocation.getValue0()).getFieldType());
        Assert.assertTrue(gameBoardService.pickFieldInLocationAndCheckIfItIsShip(firstLocation));
    }

    @Test
    void isBoardInWinState() {
        // when
        gameBoardService.setShipInLocation(firstLocation);
        gameBoardService.setShipInLocation(secondLocation);

        gameBoardService.pickFieldInLocationAndCheckIfItIsShip(firstLocation);
        gameBoardService.pickFieldInLocationAndCheckIfItIsShip(secondLocation);

        // then
        Assert.assertTrue(gameBoardService.isBoardInWinState());
    }

    @Test
    void setShipInLocation() {
        // when
        gameBoardService.setShipInLocation(firstLocation);

        // then
        Assert.assertEquals(Field.FieldType.SHIP,  gameBoardService.getGameBoard().getFieldMap()
                .get(firstLocation.getValue1()).get(firstLocation.getValue0()).getFieldType());
    }

    @Test
    void setEmptyInLocation() {
        // given
        gameBoardService.setShipInLocation(firstLocation);

        // when
        gameBoardService.setEmptyInLocation(firstLocation);

        // then
        Assert.assertEquals(Field.FieldType.EMPTY, gameBoardService.getGameBoard().getFieldMap()
                .get(firstLocation.getValue1()).get(firstLocation.getValue0()).getFieldType());
    }

    @Test
    void isBoardEmptyWhenBoardIsFilled() {
        // when
        gameBoardService.setShipInLocation(firstLocation);

        // then
        Assert.assertFalse(gameBoardService.isBoardEmpty());
    }

    @Test
    void isBoardEmptyWhenBoardIsEmpty() {
        // then
        Assert.assertTrue(gameBoardService.isBoardEmpty());
    }

    @Test
    void setRandomShips() {
        //when
        gameBoardService.setRandomShips();

        // then
        Assert.assertFalse(gameBoardService.isBoardEmpty());
    }

    @Test
    void clear() {
        // when
        gameBoardService.setRandomShips();
        gameBoardService.clear();

        // then
        Assert.assertTrue(gameBoardService.isBoardEmpty());
    }
}