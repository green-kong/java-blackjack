package blackjack.hand.running;

import blackjack.card.Cards;
import blackjack.hand.Hand;
import blackjack.hand.finished.Stay;

public class Hit extends Running {
    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public Hand stay() {
        return new Stay(getCards());
    }
}
