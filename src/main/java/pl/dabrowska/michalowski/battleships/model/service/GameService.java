package pl.dabrowska.michalowski.battleships.model.service;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.dabrowska.michalowski.battleships.model.datatype.GameBoard;
import pl.dabrowska.michalowski.battleships.model.service.cmd.ShootFieldCommand;

@RequiredArgsConstructor
public class GameService {
    private static final Integer ROW_OFFSET = 1;

    @NonNull
    @Getter
    private GameBoard gameBoard;

    public void shootField(ShootFieldCommand cmd) {
        gameBoard.setFieldPicked(cmd.getRow() - ROW_OFFSET, cmd.getColumn());
    }


}
