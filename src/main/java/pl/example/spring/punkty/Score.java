package pl.example.spring.punkty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Score {
    public final int Score;
    public final String comment;

    @JsonCreator
    public Score(@JsonProperty("score") int score, @JsonProperty("comment") String comment) {
        Score = score;
        this.comment = comment;
    }
}
