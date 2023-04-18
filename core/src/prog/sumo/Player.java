package prog.sumo;

import com.badlogic.gdx.graphics.Texture;

import prog.sumo.states.PlayState;

/**
 * Represents a player in the sumo game.
 */
public final class Player {

    /**
     * The maximum position a player can have.
     */
    private final int stepSize = PlayState.battleCircleHeight / 10;
    /**
     * The player's direction.
     */
    private final int direction;
    /**
     * The player's position.
     */
    private int position;

    private int score;

    private final Character character;

    /**
     * Constructs a new Player.
     *
     * @param character       The character the player is playing as.
     * @param playerDirection The direction the player is facing.
     */
    public Player(final Character character, final int playerDirection) {
        this.character = character;
        this.direction = playerDirection;
        if (direction == 1) {
            position = PlayState.startPosition2;
        } else {
            position = PlayState.startPosition1;
        }
        score = 0;
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

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {

        this.score++;
    }

    public Character getCharacter() {
        return character;
    }

    public int getScore() {
        return this.score;
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
                <= this.character.getTexture().getHeight()) {
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
}
