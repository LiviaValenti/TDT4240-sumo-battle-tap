package prog.sumo.models;

import com.badlogic.gdx.Gdx;

import prog.sumo.views.PlayView;

/**
 * Represents a player in the sumo game.
 */
public final class Player {

    /**
     * The maximum position a player can have.
     */
    private final int stepSize = (Gdx.graphics.getHeight() / 2) / 10;
    /**
     * The player's direction.
     */
    private final int direction;
    private final Character character;
    /**
     * The player's position.
     */
    private int position;
    private int score;

    /**
     * Constructs a new Player.
     *
     * @param playerCharacter The character the player is playing as.
     * @param playerDirection The direction the player is facing.
     */
    public Player(final Character playerCharacter, final int playerDirection) {
        this.character = playerCharacter;
        this.direction = playerDirection;
        if (direction == 1) {
            position = PlayView.startPositionOfPlayer2;
        } else {
            position = PlayView.startPositionOfPlayer1;
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
