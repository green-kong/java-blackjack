package blackjack.hand.finished;

import blackjack.card.Cards;
import blackjack.hand.Hand;
import blackjack.result.Result;

public class Bust extends Finished {
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
