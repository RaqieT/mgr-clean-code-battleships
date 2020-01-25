package pl.dabrowska.michalowski.battleships.model.service;

import lombok.Getter;
import lombok.NonNull;
import org.javatuples.Pair;
import pl.dabrowska.michalowski.battleships.exception.WrongInputException;
import pl.dabrowska.michalowski.battleships.model.datatype.Game;
import pl.dabrowska.michalowski.battleships.model.factory.GameFactory;

public class GameService {
    private static final String CAN_ADD_ONLY_IN_PREPARATION_PHASE = "You can add ship only in preparation phase";

    @NonNull
    @Getter
    private Game game;

    @NonNull
    @Getter
    private GameBoardService gameBoardService;

    public GameService() {
        this.game = GameFactory.create();
        this.gameBoardService = new GameBoardService(this.game.getBoard());
    }

    public boolean isGameFinished() {
        return this.game.getState().equals(Game.GameState.WON);
    }

    // PREPARATION PHASE
    public void addShip(Pair<Integer, Character> location) throws WrongInputException {
        if (!this.game.getState().equals(Game.GameState.PREPARATION)) {
            throw new WrongInputException(CAN_ADD_ONLY_IN_PREPARATION_PHASE);
        }

        gameBoardService.setShipInLocation(location);
    }

    public void removeShip(Pair<Integer, Character> location) throws WrongInputException {
        if (!this.game.getState().equals(Game.GameState.PREPARATION)) {
            throw new WrongInputException("You can remove ship only in preparation phase");
        }

        gameBoardService.setEmptyInLocation(location);
    }

    public void addShipsOnRandomLocations() throws WrongInputException {
        if (!this.game.getState().equals(Game.GameState.PREPARATION)) {
            throw new WrongInputException(CAN_ADD_ONLY_IN_PREPARATION_PHASE);
        }

        gameBoardService.setRandomShips();
    }

    public void startGame() throws WrongInputException {
        if (!this.game.getState().equals(Game.GameState.PREPARATION)) {
            throw new WrongInputException("You can only start game if it is in preparation phase");
        }

        if (gameBoardService.isBoardEmpty()) {
            throw new WrongInputException("You cannot start game with empty board");
        }

        this.game.setState(Game.GameState.IN_PROGRESS);
    }

    public void clearGameBoard() throws WrongInputException {
        if (!this.game.getState().equals(Game.GameState.PREPARATION)) {
            throw new WrongInputException(CAN_ADD_ONLY_IN_PREPARATION_PHASE);
        }

        gameBoardService.clear();
    }

    // IN_PROGRESS PHASE
    public String shootField(Pair<Integer, Character> location) throws WrongInputException {
        String output = "";
        if (!this.game.getState().equals(Game.GameState.IN_PROGRESS)) {
            throw new WrongInputException("You can only shoot if game is in progress phase");
        }

        if (gameBoardService.pickFieldInLocationAndCheckIfItIsShip(location)) {
            game.addScore();
            output += "Strike!";
        } else {
            output += "You missed!";
        }

        game.addShot();

        if (gameBoardService.isBoardInWinState()) {
            this.game.setState(Game.GameState.WON);
        }

        return output;
    }


}
