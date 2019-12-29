package pl.dabrowska.michalowski.battleships.model.datatype;

import lombok.Data;

@Data
public class Game {
    private GameState state;
    private GameBoard board;
    private int shots = 0;
    private int score = 0;

    public enum GameState {
        PREPARATION, IN_PROGRESS, WON
    }

    public Game(GameBoard gameBoard) {
        this.state = GameState.PREPARATION;
        this.board = gameBoard;
    }

    public void addShot() {
        shots += 1;
    }

    public void addScore() {
        score += 1;
    }
}
