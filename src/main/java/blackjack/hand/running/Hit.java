package blackjack.hand.running;

import blackjack.card.Cards;
import blackjack.hand.Hand;
import blackjack.hand.finished.Stay;
import blackjack.result.Result;

public final class Hit extends Running {
    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public Hand stay() {
        return new Stay(getCards());
    }

    @Override
    public Result battle(Hand other) {
        throw new UnsupportedOperationException();
    }
}
