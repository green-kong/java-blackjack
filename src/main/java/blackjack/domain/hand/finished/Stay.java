package blackjack.domain.hand.finished;

import blackjack.domain.card.Cards;
import blackjack.domain.card.score.Score;
import blackjack.domain.hand.Hand;
import blackjack.domain.result.Result;

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
