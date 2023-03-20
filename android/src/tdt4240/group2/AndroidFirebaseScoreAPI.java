package tdt4240.group2;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class AndroidFirebaseScoreAPI implements ScoreAPI {
    private final FirebaseDatabase database;

    public AndroidFirebaseScoreAPI() {
        database = FirebaseDatabase.getInstance("https://sumobattletap-default-rtdb.europe-west1.firebasedatabase.app/");
    }

    @Override
    public void incrementScore(String characterName) {
        // TODO
    }

    @Override
    public void getScores(Map<String, Long> scores) {
        // TODO
    }
}
