package prog.sumo.controllers;

import prog.sumo.models.Player;
import prog.sumo.utils.PlayerCollisionDetector;

public class PlayerController {
    public static void movePlayer(Player toMove, Player opponent) {
        if (PlayerCollisionDetector.collides(toMove, opponent)) {
            opponent.moveBackward();
        }
        toMove.moveForward();
    }
}
