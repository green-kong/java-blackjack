package blackjack.domain.hand.finished;

import blackjack.domain.card.Cards;
import blackjack.domain.hand.Hand;
import blackjack.domain.result.Result;

public final class Blackjack extends Finished {
    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public Result battle(Hand other) {
        if (other.isBlackjack()) {
            return Result.TIE;
        }
        return Result.BLACKJACK_WIN;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }
}
