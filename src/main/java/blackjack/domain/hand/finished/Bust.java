package blackjack.domain.hand.finished;

import blackjack.domain.card.Cards;
import blackjack.domain.hand.Hand;
import blackjack.domain.result.Result;

public final class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public Result battle(Hand other) {
        return Result.LOSE;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
