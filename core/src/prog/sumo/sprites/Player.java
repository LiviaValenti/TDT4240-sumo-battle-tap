package prog.sumo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Represents a player in the sumo game.
 */
public final class Player {

    /**
     * The maximum position a player can have.
     */
    private static final int MAX_POSITION = Gdx.graphics.getHeight() / 4 * 3;

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
        if (Math.abs(otherPlayer.getPosition() - this.getPosition()) == 1) {
            this.moveForward();
            otherPlayer.moveBackward();
        } else {
            this.moveForward();
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
