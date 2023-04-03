package blackjack.hand.finished;

import blackjack.card.Cards;
import blackjack.card.score.Score;
import blackjack.hand.Hand;
import blackjack.result.Result;

public final class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public Result battle(Hand other) {
        if (other.isBlackjack()) {
            return Result.LOSE;
        }

        if (other.isBust()) {
            return Result.WIN;
        }

        return calculateResultByScore(other);
    }

    private Result calculateResultByScore(Hand other) {
        Score score = calculateScore();
        Score otherScore = other.calculateScore();

        if (score.equals(otherScore)) {
            return Result.TIE;
        }

        if (score.isBiggerThan(otherScore)) {
            return Result.WIN;
        }

        return Result.LOSE;
    }
}
