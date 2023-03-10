package card;

import java.util.Objects;

public class Score {
    public static final int MAX_SCORE = 21;
    public static final int LIMIT_ADDITIONAL_SCORE = 11;
    public static final int ADDITIONAL_SCORE = 10;
    public static final int UNDER_SCORE = 16;
    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isMaxScore() {
        return score == MAX_SCORE;
    }

    public Score calculateAdditionalScore() {
        if (canAddAdditionalScore()) {
            return new Score(score + ADDITIONAL_SCORE);
        }
        return this;
    }

    private boolean canAddAdditionalScore() {
        return score <= LIMIT_ADDITIONAL_SCORE;
    }

    public boolean isUnderScore() {
        return score <= UNDER_SCORE;
    }

    public boolean isOverMaxScore() {
        return score > MAX_SCORE;
    }

    public int getScore() {
        return score;
    }

    public boolean isBiggerThan(Score compared) {
        return this.score > compared.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
