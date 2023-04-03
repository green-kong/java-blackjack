package blackjack.hand.finished;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.hand.AbstractHand;
import blackjack.hand.Hand;

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
