package tdt4240.group2;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AndroidFirebaseScoreAPI implements ScoreAPI {
    private final DatabaseReference scoresRef;

    public AndroidFirebaseScoreAPI() {
        scoresRef = FirebaseDatabase.getInstance("https://sumobattletap-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("scores");
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
        Map<String, Object> update = new HashMap<>();
        update.put(characterName, ServerValue.increment(1));
        scoresRef.updateChildren(update);
    }
}
