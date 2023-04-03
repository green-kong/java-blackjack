package blackjack.domain.hand.finished;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.hand.AbstractHand;
import blackjack.domain.hand.Hand;

public abstract class Finished extends AbstractHand {
    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public Hand hit(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Hand stay() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDrawable() {
        return false;
    }
}
