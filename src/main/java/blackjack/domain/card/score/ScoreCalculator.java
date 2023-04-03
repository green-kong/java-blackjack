package blackjack.domain.card.score;

public final class ScoreCalculator {
    public static Score add(Score one, Score other) {
        return new Score(one.getValue() + other.getValue());
    }
}
