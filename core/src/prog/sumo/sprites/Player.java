package prog.sumo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import prog.sumo.states.PlayState;

/**
 * Represents a player in the sumo game.
 */
public final class Player {

    /**
     * The maximum position a player can have.
     */

    private static final int MAX_POSITION_1 = PlayState.battleCircleHeight + PlayState.battleCircleRadius - 300;
    private static final int MAX_POSITION_2 = PlayState.battleCircleHeight - PlayState.battleCircleRadius + 200;

    /**
     * The player's position.
     */
    private int position;

    private int stepSize = 30;

    /**
     * The player's direction.
     */
    private int direction;

    /**
     * The player's texture.
     */
    private Texture texture;

    /**
     * Constructs a new Player.
     *
     * @param playerTexture   The texture representing the player.
     * @param playerDirection The direction the player is facing.
     */
    public Player(final Texture playerTexture, final int playerDirection) {
        this.texture = playerTexture;
        this.direction = playerDirection;
        if (direction == 1) {
            position = Gdx.graphics.getHeight() / 4 - playerTexture.getHeight() / 2;
        } else {
            position = Gdx.graphics.getHeight() / 4 * 3 - playerTexture.getHeight() / 2;
        }
    }

    /**
     * Moves the player forward.
     */
    public void moveForward() {
        if (direction == 1) {
            position += stepSize;
        } else {
            position -= stepSize;
        }
    }

    /**
     * Moves the player backward.
     */
    public void moveBackward() {
        if (direction == 1) {
            position -= stepSize;
        } else {
            position += stepSize;
        }
    }

    /**
     * Moves the player based on the other player's position.
     *
     * @param otherPlayer The other player in the game.
     */
    public void movePlayer(final Player otherPlayer) {
        if (Math.abs(otherPlayer.getPosition() - this.getPosition()) <= this.texture.getHeight()) {
            this.moveForward();
            otherPlayer.moveBackward();
        } else {
            this.moveForward();
        }

        if (this.getPosition() > MAX_POSITION_1 && otherPlayer.getPosition() > MAX_POSITION_1) {

            //Insert ROUND OVER HERE (Player 1 wins)
            this.setPosition(0);
            otherPlayer.setPosition(0);
        } else if (this.getPosition() < MAX_POSITION_2 && otherPlayer.getPosition() < MAX_POSITION_2) {

            //Insert ROUND OVER HERE (Player 2 wins)
            this.setPosition(0);
            otherPlayer.setPosition(0);
        }
    }

    /**
     * Gets the player's position.
     *
     * @return The player's position.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the player's position.
     *
     * @param newPosition The new position.
     */
    public void setPosition(final int newPosition) {
        this.position = newPosition;
    }

    /**
     * Gets the player's texture.
     *
     * @return The player's texture.
     */
    public Texture getTexture() {
        return texture;
    }
}
