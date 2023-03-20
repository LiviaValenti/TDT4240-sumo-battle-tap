package tdt4240.group2;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class AndroidFirebaseScoreAPI implements ScoreAPI {
    private final FirebaseDatabase database;
    private final DatabaseReference scoresRef;

    public AndroidFirebaseScoreAPI() {
        database = FirebaseDatabase.getInstance("https://sumobattletap-default-rtdb.europe-west1.firebasedatabase.app/");
        scoresRef = database.getReference("scores");
    }

    @Override
    public void subscribeToScores(Map<String, Long> scoresHolder) {
        scoresRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                scoresHolder.clear();
                for (DataSnapshot score : snapshot.getChildren())
                    scoresHolder.put(score.getKey(), score.getValue(Long.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.getMessage());
            }
        });
    }

    @Override
    public void incrementScore(String characterName) {

    }
}
