package blackjack.domain.card;

import blackjack.domain.card.score.Score;

public enum Rank {
    ACE(new Score(1), "A"),
    TWO(new Score(2), "2"),
    THREE(new Score(3), "3"),
    FOUR(new Score(4), "4"),
    FIVE(new Score(5), "5"),
    SIX(new Score(6), "6"),
    SEVEN(new Score(7), "7"),
    EIGHT(new Score(8), "8"),
    NINE(new Score(9), "9"),
    TEN(new Score(10), "10"),
    JACK(new Score(10), "J"),
    QUEEN(new Score(10), "Q"),
    KING(new Score(10), "K");

    private final Score score;
    private final String symbol;

    Rank(Score score, String symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    public Score getScore() {
        return score;
    }

    public String getSymbol() {
        return symbol;
    }
}
