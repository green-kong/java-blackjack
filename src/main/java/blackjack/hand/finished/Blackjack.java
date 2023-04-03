package blackjack.hand.finished;

import blackjack.card.Cards;
import blackjack.hand.Hand;
import blackjack.result.Result;

public class Blackjack extends Finished {
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
