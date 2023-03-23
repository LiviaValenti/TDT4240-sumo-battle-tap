package tdt4240.group2;

import java.util.Map;

public interface ScoreAPI {
    void incrementScore(String characterName);

    void getScores(Map<String, Long> scores);
}
