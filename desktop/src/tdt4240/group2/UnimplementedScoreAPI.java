package tdt4240.group2;

import java.util.Map;

public class UnimplementedScoreAPI implements ScoreAPI {
    @Override
    public void incrementScore(String characterName) {
        System.err.println("Score API not implemented in desktop version!");
    }

    @Override
    public void subscribeToScores(Map<String, Long> scoresHolder) {
        System.err.println("Score API not implemented in desktop version!");
    }
}
