package pl.dabrowska.michalowski.battleships.model.service;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javatuples.Pair;
import pl.dabrowska.michalowski.battleships.model.Configuration;
import pl.dabrowska.michalowski.battleships.model.datatype.Field;
import pl.dabrowska.michalowski.battleships.model.datatype.GameBoard;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@RequiredArgsConstructor
public class GameBoardService {
    private static final Configuration config = Configuration.getInstance();
    private Random random;

    {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    @NonNull
    @Getter
    private GameBoard gameBoard;

    public boolean pickFieldInLocationAndCheckIfItIsShip(Pair<Integer, Character> location) {
        Field.FieldType fieldType = gameBoard.setFieldPicked(location.getValue0(), location.getValue1());

        return fieldType.equals(Field.FieldType.SHIP);
    }

    public boolean isBoardInWinState() {
        return gameBoard.getFieldMap().entrySet().stream()
                .allMatch(entry -> entry.getValue().stream().filter(field -> field.getFieldType()
                        .equals(Field.FieldType.SHIP)).allMatch(Field::isPicked));
    }

    public void setShipInLocation(Pair<Integer, Character> location) {
        gameBoard.setFieldType(location.getValue0(), location.getValue1(), Field.FieldType.SHIP);
    }

    public void setEmptyInLocation(Pair<Integer, Character> location) {
        gameBoard.setFieldType(location.getValue0(), location.getValue1(), Field.FieldType.EMPTY);
    }

    public void setRandomShips() {
        gameBoard.getFieldMap().forEach((key, value) -> {
            for (int i = 0; i < value.size(); i++) {
                gameBoard.setFieldType(i, key, random.nextBoolean() ? Field.FieldType.SHIP : Field.FieldType.EMPTY);
            }
        });
    }

    public boolean isBoardEmpty() {
        return gameBoard.getFieldMap().values().stream().allMatch(fields -> fields.stream()
                .allMatch(field -> field.getFieldType().equals(Field.FieldType.EMPTY)));
    }

    public void clear() {
        gameBoard.getFieldMap().forEach((key, value) -> {
            for (int i = 0; i < value.size(); i++) {
                gameBoard.setFieldType(i, key, Field.FieldType.EMPTY);
            }
        });
    }
}
