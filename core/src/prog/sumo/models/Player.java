package prog.sumo.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import prog.sumo.views.PlayView;

/**
 * Represents a player in the sumo game.
 */
public final class Player {

    /**
     * The maximum position a player can have.
     */

    private static final int MAX_POSITION_1 =
            Gdx.graphics.getHeight() / 2 + PlayView.battleCircleRadius
                    - PlayView.char1.getHeight();
    private static final int MAX_POSITION_2 =
            Gdx.graphics.getHeight() / 2 - PlayView.battleCircleRadius;
    private final int stepSize = PlayView.battleCircleHeight / 10;
    /**
     * The player's direction.
     */
    private final int direction;
    /**
     * The player's texture.
     */
    private final Texture texture;
    /**
     * The player's position.
     */
    private int position;

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
            position = MAX_POSITION_2;
        } else {
            position = MAX_POSITION_1;
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
        if (Math.abs(otherPlayer.getPosition() - this.getPosition())
                <= this.texture.getHeight()) {
            this.moveForward();
            otherPlayer.moveBackward();
        } else {
            this.moveForward();
        }

        if (this.getPosition() + texture.getHeight() / 2 > MAX_POSITION_1
                && this.direction == 1) {
            //Player 1 wins
            roundOver(otherPlayer, MAX_POSITION_2, MAX_POSITION_1);
        } else if (this.getPosition() - texture.getHeight() / 2 < MAX_POSITION_2
                && this.direction == 0) {
            //Player 2 wins
            roundOver(otherPlayer, MAX_POSITION_1, MAX_POSITION_2);
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

    private void roundOver(Player otherPlayer, int thisPosition,
                           int otherPosition) {

        // todo: INCREMENT WHEN ROUND IS OVER

        this.setPosition(thisPosition);
        otherPlayer.setPosition(otherPosition);
    }
}
