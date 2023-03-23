package prog.sumo;

import java.util.Map;

/**
 * The DesktopScoreAPI class is an implementation of the ScoreAPI interface for
 * desktop games.
 */
public final class DesktopScoreAPI implements ScoreAPI {

    /**
     * Increments the score for the given character. This method should be
     * overridden for safe extension.
     *
     * @param characterName The name of the character whose score should be
     *                      incremented.
     */
    @Override
    public void incrementScore(final String characterName) {
        System.err.println("Score API not implemented in desktop version!");
    }

    /**
     * Gets the scores for all characters. This method should be overridden for
     * safe extension.
     *
     * @param scores The Map containing character names and their corresponding
     *               scores.
     */
    @Override
    public void getScores(final Map<String, Long> scores) {
        System.err.println("Score API not implemented in desktop version!");
    }
}
