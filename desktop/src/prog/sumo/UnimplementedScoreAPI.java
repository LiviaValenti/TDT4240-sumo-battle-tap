package prog.sumo;

import java.util.Map;

import prog.sumo.singletons.ScoreAPI;

/**
 * The UnimplementedScoreAPI class is an implementation of the ScoreAPI
 * interface where none of the functions are actually implemented.
 */
public final class UnimplementedScoreAPI implements ScoreAPI {

    private static UnimplementedScoreAPI instance;

    private UnimplementedScoreAPI() {
    }

    public static ScoreAPI getInstance() {
        if (instance == null) {
            instance = new UnimplementedScoreAPI();
        }
        return UnimplementedScoreAPI.instance;
    }

    /**
     * Prints an error stating that this function is unimplemented.
     *
     * @param characterName The name of the character whose score should be
     *                      incremented.
     */
    @Override
    public void incrementScore(final String characterName) {
        System.err.println("Score API not implemented in desktop version!");
    }

    /**
     * Prints an error stating that this function is unimplemented.
     *
     * @param scoresHolder The Map containing character names and their
     *                     corresponding scores.
     */
    @Override
    public void subscribeToScores(final Map<String, Long> scoresHolder) {
        System.err.println("Score API not implemented in desktop version!");
    }
}
