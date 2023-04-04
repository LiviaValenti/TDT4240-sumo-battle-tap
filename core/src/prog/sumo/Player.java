package prog.sumo;

import com.badlogic.gdx.graphics.Texture;

public class Player {
    private static final int MAX_POSITION = 4;
    private int position;
    private int direction;
    private Texture texture;

    public Player(Texture texture, int direction) {
        this.texture = texture;
        this.direction = direction;
        if (direction == 1) {
            position = 0;
        } else {
            position = MAX_POSITION;
        }
    }

    public void moveForward() {
        if (direction == 1) {
            position++;
        } else {
            position--;
        }
    }

    public void moveBackward() {
        if (direction == 1) {
            position--;
        } else {
            position++;
        }
    }

    public void movePlayer(Player otherPlayer) {
        if (Math.abs(otherPlayer.getPosition() - this.getPosition()) == 1) {
            this.moveForward();
            otherPlayer.moveBackward();
        } else {
            this.moveForward();
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Texture getTexture() {
        return texture;
    }
}
