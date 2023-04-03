package blackjack.hand.running;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.hand.AbstractHand;
import blackjack.hand.Hand;
import blackjack.hand.finished.Blackjack;
import blackjack.hand.finished.Bust;

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
