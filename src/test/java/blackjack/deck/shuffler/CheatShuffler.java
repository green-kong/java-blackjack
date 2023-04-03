package blackjack.deck.shuffler;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.deck.shuffler.Shuffler;

public class CheatShuffler implements Shuffler {
    private final List<Card> cards;

    public CheatShuffler(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> shuffle(List<Card> deck) {
        return cards;
    }
}
