package pl.dabrowska.michalowski.battleships.model.service;

import org.javatuples.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.dabrowska.michalowski.battleships.exception.WrongInputException;
import pl.dabrowska.michalowski.battleships.model.datatype.Field;
import pl.dabrowska.michalowski.battleships.model.datatype.Game;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GameServiceTest {

    private GameService gameService;
    private Pair<Integer, Character> firstLocation;
    private Pair<Integer, Character> secondLocation;

    @BeforeEach
    void setUp() {
        // given
        gameService = new GameService();
        firstLocation = new Pair<>(5, 'A');
        secondLocation = new Pair<>(5, 'B');
    }


    @Test
    void isGameFinished() throws WrongInputException {
        // when
        gameService.addShip(firstLocation);
        gameService.addShip(secondLocation);
        gameService.startGame();
        gameService.shootField(firstLocation);
        gameService.shootField(secondLocation);

        // then
        Assert.assertTrue(gameService.isGameFinished());
    }

    @Test
    void addShip() throws WrongInputException {
        // when
        gameService.addShip(firstLocation);

        // then
        Field.FieldType actual = gameService.getGame().getBoard().getFieldMap().get(firstLocation.getValue1())
                .get(firstLocation.getValue0()).getFieldType();
        Field.FieldType expected = Field.FieldType.SHIP;
        Assert.assertEquals(expected, actual);
    }

    @Test
    void removeShip() throws WrongInputException {
        // when
        gameService.addShip(secondLocation);
        gameService.removeShip(secondLocation);

        // then
        Assert.assertEquals(Field.FieldType.EMPTY, gameService.getGame().getBoard().getFieldMap()
                .get(secondLocation.getValue1()).get(secondLocation.getValue0()).getFieldType());
    }

    @Test
    void addShipsOnRandomLocations() throws WrongInputException {
        // given
        Map<Character, List<Field>> fieldMap = gameService.getGame().getBoard().getFieldMap();
        boolean isBoardFilled = false;

        // when
        gameService.clearGameBoard();
        gameService.addShipsOnRandomLocations();

        for (List<Field> fields : fieldMap.values()) {
            for (Field field : fields) {
                if (field.getFieldType() == Field.FieldType.SHIP) {
                    isBoardFilled = true;
                    break;
                }
            }
        }

        // then
        Assert.assertTrue(isBoardFilled);
    }

    @Test
    void startGame() throws WrongInputException {
        // when
        gameService.addShip(firstLocation);
        gameService.startGame();

        // then
        Assert.assertEquals(Game.GameState.IN_PROGRESS, gameService.getGame().getState());
    }

    @Test
    void clearGameBoard() throws WrongInputException {
        // when
        gameService.clearGameBoard();

        // then
        Field.FieldType actual1 = gameService.getGame().getBoard().getFieldMap().get(firstLocation.getValue1())
                .get(firstLocation.getValue0()).getFieldType();
        Field.FieldType actual2 = gameService.getGame().getBoard().getFieldMap().get(secondLocation.getValue1())
                .get(secondLocation.getValue0()).getFieldType();
        Assert.assertEquals(Field.FieldType.EMPTY, actual1);
        Assert.assertEquals(Field.FieldType.EMPTY, actual2);
    }

    @Test
    void shootField() throws WrongInputException {
        // when
        gameService.addShip(firstLocation);
        gameService.startGame();
        gameService.shootField(firstLocation);

        // then
        Assert.assertTrue(gameService.getGame().getBoard().getFieldMap().get(firstLocation.getValue1())
                .get(firstLocation.getValue0()).isPicked());
    }

    @Test
    void shootFieldShouldThrowExceptionWhenGameInPreparationState() {
        // then
        assertThrows(WrongInputException.class,
                ()-> gameService.shootField(firstLocation));
    }

    @Test
    void addShipShouldThrowException() throws WrongInputException {
        // when
        gameService.addShip(firstLocation);
        gameService.startGame();

        // then
        assertThrows(WrongInputException.class,
                ()-> gameService.addShip( new Pair<>(5, 'C')));
    }

    @Test
    void addShipOnRandomLocationsShouldThrowException() throws WrongInputException {
        // when
        gameService.addShip(firstLocation);
        gameService.startGame();

        // then
        assertThrows(WrongInputException.class,
                ()-> gameService.addShipsOnRandomLocations());
    }

    @Test
    void removeShipShouldThrowException() throws WrongInputException {
        // when
        gameService.addShip(firstLocation);
        gameService.startGame();

        // then
        assertThrows(WrongInputException.class,
                ()-> gameService.removeShip(firstLocation));
    }

    @Test
    void clearGameBoardShouldThrowException() throws WrongInputException {
        // when
        gameService.addShip(firstLocation);
        gameService.startGame();

        // then
        assertThrows(WrongInputException.class,
                ()-> gameService.clearGameBoard());
    }

    @Test
    void startGameShouldThrowExceptionWhenStartedAgain() throws WrongInputException {
        // when
        gameService.addShip(firstLocation);
        gameService.startGame();

        // then
        assertThrows(WrongInputException.class,
                ()-> gameService.startGame());
    }

    @Test
    void startGameShouldThrowExceptionWhenStartedWithEmptyBoard() {
        // then
        assertThrows(WrongInputException.class,
                ()-> gameService.startGame());
    }

}