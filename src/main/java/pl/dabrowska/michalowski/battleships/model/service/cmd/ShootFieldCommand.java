package pl.dabrowska.michalowski.battleships.model.service.cmd;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ShootFieldCommand {
    private int row;
    private char column;
}
