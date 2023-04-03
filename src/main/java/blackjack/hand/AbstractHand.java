package blackjack.hand;

import blackjack.card.Cards;
import blackjack.card.score.Score;

public abstract class AbstractHand implements Hand {
    protected final Cards cards;

    public AbstractHand(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public Score calculateScore() {
        return cards.calculateScoreSum();
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
