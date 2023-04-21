package prog.sumo.utils;

import prog.sumo.models.Player;

public final class PlayerCollisionDetector {

    private PlayerCollisionDetector() {
    }

    public static boolean collides(Player p1, Player p2) {
        return Math.abs(p2.getPosition() - p1.getPosition())
                <= p1.getCharacter().getTexture().getHeight();
    }
}
