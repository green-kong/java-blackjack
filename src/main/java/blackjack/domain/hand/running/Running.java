package blackjack.domain.hand.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.hand.AbstractHand;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.finished.Blackjack;
import blackjack.domain.hand.finished.Bust;

public abstract class Running extends AbstractHand {
    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public Hand hit(Card card) {
        cards.addCard(card);
        return calculateState();
    }

    private Hand calculateState() {
        if (cards.isBust()) {
            return new Bust(getCards());
        }
        if (cards.isBlackjack()) {
            return new Blackjack(getCards());
        }
        return this;
    }

    @Override
    public boolean isDrawable() {
        return true;
    }
}
