package blackjack.domain.hand.running;

import blackjack.domain.card.Cards;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.finished.Stay;
import blackjack.domain.result.Result;

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
