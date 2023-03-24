/**
 * The ScoreAPI interface defines the methods for managing player scores in a
 * game.
 */
package prog.sumo;

import java.util.Map;

public interface ScoreAPI {

    /**
     * Increments the score of a given character by 1.
     *
     * @param characterName The name of the character whose score is to be
     *                      incremented.
     */
    void incrementScore(String characterName);

    /**
     * Subscribes to the scores of all characters in the game.
     *
     * @param scoresHolder A map for holding the scores where the keys are
     *                     character names and the values are their respective
     *                     scores.
     */
    void subscribeToScores(Map<String, Long> scoresHolder);
}
