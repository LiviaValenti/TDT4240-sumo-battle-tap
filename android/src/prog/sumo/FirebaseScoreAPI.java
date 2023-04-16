package prog.sumo;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Android implementation of the ScoreAPI using Firebase.
 */
public final class FirebaseScoreAPI implements ScoreAPI {

    private static FirebaseScoreAPI instance;


    public static FirebaseScoreAPI getInstance() {
        if (instance == null) {
            instance = new FirebaseScoreAPI();
        }
        return FirebaseScoreAPI.instance;
    }

    /**
     * Reference to the database entry of the scores.
     */
    private final DatabaseReference scoresRef;

    /**
     * Constructor for the FirebaseScoreAPI.
     */
    private FirebaseScoreAPI() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(
                "https://sumobattletap-default-rtdb.europe-"
                        + "west1.firebasedatabase.app/"
        );
        scoresRef = database.getReference("scores");
    }

    @Override
    public void incrementScore(final String characterName) {
        Map<String, Object> update = new HashMap<>();
        update.put(characterName, ServerValue.increment(1));
        scoresRef.updateChildren(update);
    }

    @Override
    public void subscribeToScores(final Map<String, Long> scoresHolder) {
        scoresRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {
                scoresHolder.clear();
                for (DataSnapshot score : snapshot.getChildren()) {
                    scoresHolder.put(score.getKey(),
                            score.getValue(Long.class));
                }
            }

            @Override
            public void onCancelled(@NonNull final DatabaseError error) {
                System.out.println(error.getMessage());
            }
        });
    }
}
