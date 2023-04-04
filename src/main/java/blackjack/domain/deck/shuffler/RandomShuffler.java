package blackjack.domain.deck.shuffler;

import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;

public final class RandomShuffler implements Shuffler {
    @Override
    public List<Card> shuffle(List<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }
}