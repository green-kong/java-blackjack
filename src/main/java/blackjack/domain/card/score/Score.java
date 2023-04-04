package blackjack.domain.card.score;

import java.util.Objects;

public final class Score {
    public static final Score LIMIT_SCORE = new Score(21);
    public static final Score ZERO_SCORE = new Score(0);
    public static final Score ACE_ADDITIONAL_SCORE = new Score(10);
    public static final Score DEALER_DRAW_LIMIT = new Score(16);
    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public boolean isAcceptableAdditionalScore() {
        return this.value <= 11;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean isBiggerThan(Score other) {
        return this.value > other.value;
    }
}