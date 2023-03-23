package prog.sumo;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

/**
 * Android implementation of the ScoreAPI using Firebase.
 */
public class AndroidFirebaseScoreAPI implements ScoreAPI {
    /**
     * Constructor for the AndroidFirebaseScoreAPI.
     */
    private final FirebaseDatabase database;

    /**
     * Constructor for the AndroidFirebaseScoreAPI.
     */
    public AndroidFirebaseScoreAPI() {
        database = FirebaseDatabase.getInstance(
                "https://sumobattletap-default-rtdb.europe-"
                        + "west1.firebasedatabase.app/"
        );
    }

    @Override
    public void incrementScore(final String characterName) {
        // TODO
    }

    @Override
    public void getScores(final Map<String, Long> scores) {
        // TODO
    }
}
